package chester.resolve

import chester.syntax.accociativity.*
import chester.syntax.*
import chester.syntax.concrete.*
import chester.error.*
import chester.tyck.Reporter
import scalax.collection.immutable.Graph
import scalax.collection.edges.DiEdge

import scala.collection.mutable

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

def precedenceOf(
                  opInfo: OpInfo,
                  groupPrecedence: Map[QualifiedIDString, Int],
                  reporter: Reporter[TyckError]
                ): Int = {
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

def associativityOf(opInfo: OpInfo): Associativity = {
  opInfo match {
    case op: OpWithGroup => op.group.associativity
    case _ => Associativity.None
  }
}

def determineOpType(
                     tokenInfo: TokenInfo,
                     prevToken: Option[TokenInfo],
                     nextToken: Option[TokenInfo]
                   ): OpType = {
  val isPrevOperand = prevToken.exists(_.possibleOpTypes.contains(OpType.Operand))
  val isNextOperand = nextToken.exists(_.possibleOpTypes.contains(OpType.Operand))

  val possibleTypes = tokenInfo.possibleOpTypes

  if (possibleTypes.contains(OpType.Infix) && isPrevOperand && isNextOperand) {
    OpType.Infix
  } else if (possibleTypes.contains(OpType.Prefix) && !isPrevOperand && isNextOperand) {
    OpType.Prefix
  } else if (possibleTypes.contains(OpType.Postfix) && isPrevOperand && !isNextOperand) {
    OpType.Postfix
  } else if (possibleTypes.contains(OpType.Operand)) {
    OpType.Operand
  } else {
    // Default to Operand if unable to determine
    OpType.Operand
  }
}

def parseTokens(
                 seq: Vector[Expr],
                 opContext: OperatorsContext,
                 groupPrecedence: Map[QualifiedIDString, Int],
                 reporter: Reporter[TyckError]
               ): Vector[TokenInfo] = {
  seq.map {
    case id@Identifier(name, _) =>
      val possibleOps = Seq(
        opContext.resolveInfix(name).map(op => (op, OpType.Infix)),
        opContext.resolvePrefix(name).map(op => (op, OpType.Prefix)),
        opContext.resolvePostfix(name).map(op => (op, OpType.Postfix))
      ).flatten

      if (possibleOps.nonEmpty) {
        val possibleOpTypes = possibleOps.map(_._2).toSet
        val precedences = possibleOps.map { case (opInfo, _) =>
          precedenceOf(opInfo, groupPrecedence, reporter)
        }
        val associativities = possibleOps.map { case (opInfo, _) =>
          associativityOf(opInfo)
        }
        // For simplicity, take the lowest precedence and the first associativity
        val precedence = precedences.min
        val associativity = associativities.head

        TokenInfo(id, precedence, associativity, possibleOpTypes, possibleOps)
      } else {
        reporter.apply(UnknownOperator(id))
        TokenInfo(id, Int.MaxValue, Associativity.None, Set(OpType.Operand))
      }
    case expr =>
      TokenInfo(expr, Int.MaxValue, Associativity.None, Set(OpType.Operand))
  }
}
def buildExpr(
               stack: mutable.Stack[TokenInfo],
               opContext: OperatorsContext,
               reporter: Reporter[TyckError]
             ): Expr = {
  if (stack.isEmpty) {
    reporter.apply(UnexpectedTokens(Nil))
    Identifier("error")
  } else {
    val tokenInfo = stack.pop()
    val expr = tokenInfo.expr
    val opType = tokenInfo.possibleOpTypes.headOption.getOrElse(OpType.Operand)

    opType match {
      case OpType.Infix =>
        val right = buildExpr(stack, opContext, reporter)
        val left = buildExpr(stack, opContext, reporter)
        InfixExpr(left, expr.asInstanceOf[Identifier], right, expr.meta)
      case OpType.Prefix =>
        val operand = buildExpr(stack, opContext, reporter)
        PrefixExpr(expr.asInstanceOf[Identifier], operand, expr.meta)
      case OpType.Postfix =>
        val operand = buildExpr(stack, opContext, reporter)
        PostfixExpr(operand, expr.asInstanceOf[Identifier], expr.meta)
      case OpType.Operand =>
        expr
    }
  }
}
def shouldPopOperator(
                       topOperator: TokenInfo,
                       currentPrec: Int,
                       currentAssoc: Associativity
                     ): Boolean = {
  val topPrec = topOperator.precedence
  if (currentAssoc == Associativity.Left) {
    topPrec <= currentPrec
  } else {
    topPrec < currentPrec
  }
}
def parseExpression(
                     tokens: Vector[TokenInfo],
                     opContext: OperatorsContext,
                     reporter: Reporter[TyckError]
                   ): Expr = {
  val output = mutable.Stack[TokenInfo]()
  val operators = mutable.Stack[TokenInfo]()

  tokens.zipWithIndex.foreach { case (tokenInfo, index) =>
    val prevToken = if (index > 0) Some(tokens(index - 1)) else None
    val nextToken = if (index < tokens.length - 1) Some(tokens(index + 1)) else None

    val opType = determineOpType(tokenInfo, prevToken, nextToken)
    val tokenInfoWithType = tokenInfo.copy(possibleOpTypes = Set(opType))
    opType match {
      case OpType.Operand =>
        output.push(tokenInfoWithType)
      case OpType.Prefix | OpType.Postfix | OpType.Infix =>
        while (
          operators.nonEmpty && shouldPopOperator(
            operators.top,
            tokenInfo.precedence,
            tokenInfo.associativity
          )
        ) {
          output.push(operators.pop())
        }
        operators.push(tokenInfoWithType)
      case _ =>
        reporter.apply(UnknownOperator(tokenInfo.expr))
    }
  }
  while (operators.nonEmpty) {
    output.push(operators.pop())
  }

  val expr = buildExpr(output, opContext, reporter)
  if (output.nonEmpty) {
    reporter.apply(UnexpectedTokens(output.map(_.expr).toList))
  }
  expr
}

sealed trait OpType

object OpType {
  case object Infix extends OpType

  case object Prefix extends OpType

  case object Postfix extends OpType

  case object Operand extends OpType
}

case class TokenInfo(
                      expr: Expr,
                      precedence: Int,
                      associativity: Associativity,
                      possibleOpTypes: Set[OpType],
                      opInfos: Seq[(OpInfo, OpType)] = Seq.empty
                    )

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
      // Convert to LayeredTopologicalOrder to access layers
      val layeredOrder = order.toLayered
      // Map each group to its precedence level
      layeredOrder.iterator
        .flatMap { case (index: Int, nodes: Iterable[precedenceGraph.NodeT]) =>
          nodes.map { (node: precedenceGraph.NodeT) =>
            node.outer.name -> index
          }
        }
        .toMap
    case Left(_) =>
      // If there's a cycle, report an error
      reporter.apply(PrecedenceCycleDetected(precedenceGraph.nodes.map(_.outer)))
      Map.empty
  }

  val tokens = parseTokens(opSeq.seq, opContext, groupPrecedence, reporter)

  // Collect operator groups from tokens
  val operatorGroups = tokens.flatMap { tokenInfo =>
    tokenInfo.opInfos.collect {
      case (op: OpWithGroup, _) => op.group
    }
  }

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
    reporter.apply(UnconnectedPrecedenceGroups(group1, group2))
  }

  parseExpression(tokens, opContext, reporter)
}