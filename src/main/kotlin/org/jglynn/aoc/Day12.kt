package org.jglynn.aoc

import java.util.*

class Day12(private val input: Graph<String>)  {

    fun solvePart1(): Int {
        return solve(::canVisitPart1)
    }

    fun solvePart2(): Int {
        return solve(::canVisitPart2)
    }

    // Uppercase nodes can be visited more than once
    // All other nodes only once
    private fun canVisitPart1(visitedNodes: List<String>, node: String): Boolean =
        node.first().isUpperCase() or (node !in visitedNodes)

    // Uppercase nodes can be visited more than once
    // Only one lowercase node can be visited twice
    // start cannot be revisited
    private fun canVisitPart2(visitedNodes: List<String>, node: String): Boolean =
        when {
            node.first().isUpperCase() -> true
            node !in visitedNodes -> true
            node == "start" -> false
            else -> visitedNodes
                .filterNot { it.first().isUpperCase() }
                .groupingBy { it }
                .eachCount()
                .none { it.value == 2 }
        }

    private fun solve(canVisit: (nodes: List<String>, node: String) -> Boolean ): Int {
        val dfs = DFS(canVisit)
        dfs.enumerate(input,"start", "end")
        return dfs.allPaths.size
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
                for (nextNode in graph.getAdjacencyEntry(startNode)) {
                    if (canVisit(onPath, nextNode)) enumerate(graph, nextNode, endNode)
                }
            }

            path.pop()
            onPath.remove(startNode)
        }

    }
}