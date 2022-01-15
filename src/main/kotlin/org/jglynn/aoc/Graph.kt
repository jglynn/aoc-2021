package org.jglynn.aoc

class Graph<T> {
    private val adjacencyMap: MutableMap<T, MutableSet<T>> = mutableMapOf()

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

    fun getAdjacencyEntry(startNode: T): MutableSet<T> =
        adjacencyMap.getOrDefault(startNode, mutableSetOf<T>())

    override fun toString(): String = StringBuffer().apply {
        for (key in adjacencyMap.keys) {
            append("$key -> ")
            append(adjacencyMap[key]?.joinToString(", ", "[", "]\n"))
        }
    }.toString()
}