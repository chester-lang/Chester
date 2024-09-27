package chester.resolve

import chester.syntax.accociativity.*
import chester.syntax.*
import chester.syntax.concrete.*
import chester.error.*
import chester.tyck.Reporter
import scalax.collection.immutable.Graph
import scalax.collection.edges.DiEdge

def constructPrecedenceGraph(ctx: PrecedenceGroupCtx): Graph[PrecedenceGroup, DiEdge[PrecedenceGroup]] = {
  // Extract the list of PrecedenceGroups from the context
  val groups: Seq[PrecedenceGroup] = ctx.groups

  // Create directed edges based on the 'higherThan' and 'lowerThan' relationships
  val edges: Seq[DiEdge[PrecedenceGroup]] = groups.flatMap { group =>
    // Edges from the group to groups it is higher than
    val higherEdges = group.higherThan.map { higherGroup =>
      new DiEdge[PrecedenceGroup](group, higherGroup)
    }

    // Edges from groups it is lower than to the group
    val lowerEdges = group.lowerThan.map { lowerGroup =>
      new DiEdge[PrecedenceGroup](lowerGroup, group)
    }

    higherEdges ++ lowerEdges
  }

  // Construct the graph from the nodes and edges
  val graph = Graph.from(nodes = groups, edges = edges)

  // Check for cycles in the graph
  if (graph.isCyclic) {
    throw new IllegalArgumentException("The precedence graph contains cycles")
  }

  graph
}

def resolveOpSeq(
                  reporter: Reporter[TyckError],
                  opContext: OperatorsContext,
                  opSeq: OpSeq
                ): Expr = {

  // Construct the precedence graph
  val precedenceGraph = constructPrecedenceGraph(opContext.groups)
  // Perform a topological sort of the precedence graph
  val topOrder = precedenceGraph.topologicalSort

  val groupPrecedence: Map[QualifiedIDString, Int] = topOrder match {
    case Right(order) =>
      // Map each group to its precedence level
      order.layers
        .zipWithIndex
        .flatMap { case (layer: Iterable[precedenceGraph.NodeT], index: Int) =>
          layer.map { node: precedenceGraph.NodeT =>
            node.value.name -> index
          }
        }
        .toMap
    case Left(_) =>
      // If there's a cycle, report an error
      reporter.report(TyckError.PrecedenceCycleDetected(precedenceGraph.nodes.map(_.value)))
      Map.empty
  }

  // Helper function to get the precedence of an operator
  def precedenceOf(opInfo: OpInfo): Int = {
    opInfo match {
      case op: OpWithGroup =>
        val groupName = op.group.name
        groupPrecedence.getOrElse(groupName, {
          // Report unknown precedence group
          reporter.apply(UnknownPrecedenceGroup(op.group))
          Int.MaxValue
        })
      case _ =>
        // Assign default precedence for unary operators
        groupPrecedence.getOrElse(DefaultPrecedenceGroup.name, Int.MaxValue)
    }
  }

  // Helper function to get the associativity of an operator
  def associativityOf(opInfo: OpInfo): Associativity = {
    opInfo match {
      case op: OpWithGroup => op.group.associativity
      case _ => Associativity.None
    }
  }

  // Helper function to parse the OpSeq into tokens with their precedence
  def parseTokens(seq: Vector[Expr]): Vector[(Expr, Int, Associativity)] = {
    seq.map {
      case id@Identifier(name, _) =>
        opContext.resolveOperator(name) match {
          case Some(opInfo) =>
            val precedence = precedenceOf(opInfo)
            val associativity = associativityOf(opInfo)
            (id, precedence, associativity)
          case None =>
            reporter.apply(UnknownOperator(id))
            (id, Int.MaxValue, Associativity.None)
        }
      case expr =>
        // Operands have lowest precedence
        (expr, Int.MaxValue, Associativity.None)
    }
  }

  // Parsing expression using a shunting-yard algorithm
  def parseExpression(tokens: Vector[(Expr, Int, Associativity)]): Expr = {
    import scala.collection.mutable.Stack

    val output = Stack[Expr]()
    val operators = Stack[(Expr, Int, Associativity)]()

    tokens.foreach { case (token, prec, assoc) =>
      token match {
        case id: Identifier =>
          opContext.resolveOperator(id.name) match {
            case Some(opInfo) =>
              // It's an operator
              while (operators.nonEmpty && {
                val (op2, prec2, assoc2) = operators.head
                (assoc match {
                  case Associativity.Left => prec <= prec2
                  case Associativity.Right => prec < prec2
                  case Associativity.None => prec < prec2
                })
              }) {
                output.push(operators.pop()._1)
              }
              operators.push((token, prec, assoc))
            case None =>
              // It's an operand
              output.push(token)
          }
        case _ =>
          // It's an operand
          output.push(token)
      }
    }

    while (operators.nonEmpty) {
      output.push(operators.pop()._1)
    }

    // Build the expression tree from the output stack
    def buildExpr(stack: Stack[Expr]): Expr = {
      if (stack.isEmpty) {
        reporter.apply(UnexpectedTokens(Nil))
        Identifier("error")
      } else {
        val expr = stack.pop()
        expr match {
          case id@Identifier(name, _) =>
            opContext.resolveOperator(name) match {
              case Some(opInfo) =>
                opInfo match {
                  case _: Infix =>
                    val right = buildExpr(stack)
                    val left = buildExpr(stack)
                    // Construct the infix expression
                    InfixExpr(left, id, right, expr.meta)
                  case _: Prefix =>
                    val operand = buildExpr(stack)
                    // Construct the prefix expression
                    PrefixExpr(id, operand, expr.meta)
                  case _: Postfix =>
                    val operand = buildExpr(stack)
                    // Construct the postfix expression
                    PostfixExpr(operand, id, expr.meta)
                  case _ =>
                    reporter.apply(UnknownOperator(id))
                    id
                }
              case None =>
                reporter.apply(UnknownOperator(id))
                id
            }
          case other =>
            other
        }
      }
    }

    val expr = buildExpr(output)
    // Check if there are any unprocessed tokens
    if (output.nonEmpty) {
      reporter.apply(UnexpectedTokens(output.toList))
    }
    expr
  }

  val tokens = parseTokens(opSeq.seq)

  // Check for operators with unconnected precedence groups
  val operatorGroups = tokens.collect {
    case (id: Identifier, _, _) =>
      opContext.resolveOperator(id.name) match {
        case Some(op: OpWithGroup) => op.group
        case _ => None
      }
  }.collect { case group: PrecedenceGroup => group }
  // Verify that all operator groups are connected in the precedence graph
  for {
    group1 <- operatorGroups
    group2 <- operatorGroups
    if group1 != group2
    node1Option = precedenceGraph.find(group1)
    node2Option = precedenceGraph.find(group2)
    if node1Option.isDefined && node2Option.isDefined
    node1 = node1Option.get
    node2 = node2Option.get
    // Check if no path exists between node1 and node2 in either direction
    if node1.pathTo(node2).isEmpty && node2.pathTo(node1).isEmpty
  } {
    reporter.report(TyckError.UnconnectedPrecedenceGroups(group1, group2))
  }
  parseExpression(tokens)
}