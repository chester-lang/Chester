package chester.resolve
import chester.syntax.accociativity.{PrecedenceGroup, PrecedenceGroupCtx}
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
  val graph: Graph[PrecedenceGroup, DiEdge[PrecedenceGroup]] = Graph.from(nodes = groups, edges = edges)

  // Check for cycles in the graph
  if (graph.isCyclic) {
    throw new IllegalArgumentException("The precedence graph contains cycles")
  }

  graph
}