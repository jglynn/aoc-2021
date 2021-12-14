import java.util.Stack

class Graph<T> {
    val adjacencyMap: MutableMap<T, MutableSet<T>> = mutableMapOf()

    fun addEdge(sourceVertex: T, destinationVertex: T) {
        // Add edge to source vertex / node.
        adjacencyMap
            .computeIfAbsent(sourceVertex) { mutableSetOf<T>() }
            .add(destinationVertex)
        // Add edge to destination vertex / node.
        adjacencyMap
            .computeIfAbsent(destinationVertex) { mutableSetOf<T>() }
            .add(sourceVertex)
    }

    override fun toString(): String = StringBuffer().apply {
        for (key in adjacencyMap.keys) {
            append("$key -> ")
            append(adjacencyMap[key]?.joinToString(", ", "[", "]\n"))
        }
    }.toString()
}

class DFS (
    val canVisit: (MutableList<String>, String) -> Boolean
) {
    private val path = Stack<String>() // the current path
    private val onPath = mutableListOf<String>() // the set of nodes on the path
    val allPaths = mutableListOf<List<String>>() // all viable paths found

    // Perform a Depth First Search
    fun enumerate(graph: Graph<String>, startNode: String, endNode: String) {

        // add node v to current path from s
        path.push(startNode)
        onPath.add(startNode)

        // found path from s to t - currently prints in reverse order because of stack
        if (startNode == endNode) {
            allPaths.add(path.toList())
        } else {
            for (nextNode in graph.adjacencyMap.getOrDefault(startNode, emptySet<String>())) {
                if (canVisit(onPath, nextNode)) enumerate(graph, nextNode, endNode)
            }
        }

        // done exploring from v, so remove from path
        path.pop()
        onPath.remove(startNode)
    }

}