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

class DFS (val canVisit: (List<String>, String) -> Boolean) {
    private val path = Stack<String>() // the current path
    private val onPath = mutableListOf<String>() // the set of nodes on the path
    val allPaths = mutableListOf<List<String>>() // all viable paths found

    // Perform a Depth First Search
    fun enumerate(graph: Graph<String>, startNode: String, endNode: String) {

        path.push(startNode)
        onPath.add(startNode)

        if (startNode == endNode) {
            allPaths.add(path.toList())
        } else {
            for (nextNode in graph.adjacencyMap.getOrDefault(startNode, emptySet<String>())) {
                if (canVisit(onPath, nextNode)) enumerate(graph, nextNode, endNode)
            }
        }

        path.pop()
        onPath.remove(startNode)
    }

}

fun <T> List<Pair<T, T>>.getUniqueValuesFromPairs(): Set<T> = this
    .map { (a, b) -> listOf(a, b) }
    .flatten()
    .toSet()

fun <T> List<Pair<T, T>>.getUniqueValuesFromPairs(predicate: (T) -> Boolean): Set<T> = this
    .map { (a, b) -> listOf(a, b) }
    .flatten()
    .filter(predicate)
    .toSet()

data class DGraph<T>(
    val vertices: Set<T>,
    val edges: Map<T, Set<T>>,
    val weights: Map<Pair<T, T>, Int>
) {
    constructor(weights: Map<Pair<T, T>, Int>): this(
        vertices = weights.keys.toList().getUniqueValuesFromPairs(),
        edges = weights.keys
            .groupBy { it.first }
            .mapValues { it.value.getUniqueValuesFromPairs { x -> x !== it.key } }
            .withDefault { emptySet() },
        weights = weights
    )
}