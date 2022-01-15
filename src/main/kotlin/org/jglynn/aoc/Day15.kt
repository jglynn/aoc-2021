package org.jglynn.aoc

import java.util.*

class Day15(private val input: List<String>) {

    fun solvePart1(): Int {
        val graph = buildGraph()
        val d = Dijkstra(graph)
        return d.distance(0,graph.size()-1)
    }

    private fun buildGraph(): WeightedGraph {
        val lineSize = input.first().length
        val nodes = input
            .joinToString("") { it }
            .takeWhile { it.isDigit() }
            .map { it.digitToInt()}
            .toList()

        val nodeMap = mutableMapOf<Pair<Int,Int>, Int>()
        val graph = WeightedGraph(nodes.size)

        var col = 1
        for(i in 0 until nodes.size) {
            if(col++ < lineSize) {
                nodeMap.put(Pair(i, i + 1), nodes[i+1])
                graph.addEdge(i, i+1, nodes[i+1])
            } else col = 1
            if(i < (nodes.size - lineSize)) {
                nodeMap.put(Pair(i, i + lineSize), nodes[i+lineSize])
                graph.addEdge(i, i+lineSize, nodes[i+lineSize])
            }
        }

        return graph

    }


    private class WeightedGraph(private val maxNodes: Int) {

        private val adjacencyMap = HashMap<Int, MutableMap<Int, Int>>()

        fun addEdge(edge: Edge) {
            addEdge(edge.src, edge.dst, edge.len)
        }

        fun addEdge(srcNode: Int, dstNode: Int, weight: Int) {
            if (weight < 0) {
                throw IllegalArgumentException("Weight must be greater than 0")
            }
            if (srcNode < 0 || srcNode > maxNodes || dstNode < 0 || dstNode > maxNodes) {
                throw IllegalArgumentException("Node Id numbers must be between 1 and $maxNodes")
            }

            addOneWayEdge(srcNode, dstNode, weight)
            addOneWayEdge(dstNode, srcNode, weight)
        }

        private fun addOneWayEdge(src: Int, dst: Int, weight: Int) {
            if (adjacencyMap.containsKey(src)) {
                adjacencyMap[src]?.put(dst, weight)
            } else {
                val map = HashMap<Int, Int>()
                map[dst] = weight
                adjacencyMap[src] = map
            }
        }

        fun edgeWeight(srcNode: Int, dstNode: Int): Int? {
            return adjacencyMap[srcNode]?.get(dstNode)
        }

        fun nodeNeighbours(node: Int): Set<Edge>? {
            return adjacencyMap[node]?.entries?.mapTo(mutableSetOf()) {
                    it -> Edge(node, it.key, it.value)
            }
        }

        fun nodes(): Set<Int> {
            return adjacencyMap.keys.toSet()
        }

        fun hasNode(node: Int): Boolean {
            return adjacencyMap[node] != null
        }

        fun size(): Int {
            return maxNodes
        }
    }

    private class Edge(val src: Int, val dst: Int, val len: Int) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (this.javaClass != other?.javaClass) return false

            other as Edge

            if (src != other.src) {
                return (src == other.dst) && (dst == other.src)
            }
            if (dst != other.dst) return false
            if (len != other.len) return false

            return true
        }

        override fun hashCode(): Int {
            var result = (src + dst) * Math.abs(src - dst) * 31
            result = 31 * result + len
            return result
        }

        override fun toString(): String {
            return "{$src <-($len)-> $dst}"
        }
    }

    private class Dijkstra(private val graph: WeightedGraph) {

        fun distance(srcNode: Int, dstNode: Int): Int {
            val dist = Array(graph.size()) {-1}
            val prev = Array(graph.size()) {-1}
            val Q = graph.nodes().toMutableSet()
            dist[srcNode] = 0
            while(Q.size > 0) {
                val u = min(dist, Q)
                if (u == dstNode) {
                    break
                }
                Q.remove(u)
                graph.nodeNeighbours(u)?.forEach { edge ->
                    val alt = dist[u] + edge.len
                    if (dist[edge.dst] == -1 || alt < dist[edge.dst]) {
                        dist[edge.dst] = alt
                        prev[edge.dst] = u
                    }
                } ?: throw Exception("No path found")
            }
            val S = Stack<Int>()
            var u = dstNode
            while (prev[u] > -1) {
                S.push(u)
                u = prev[u]
            }
            S.push(u)
            var s = S.pop()
            var pathDist = 0
            for(j in 0 .. (S.size - 1)) {
                val d = S.pop()
                pathDist += graph.edgeWeight(s, d) ?: 0
                //println("added $s to $d")
                s = d
            }
            return pathDist
        }

        fun min(dist: Array<Int>, Q: Set<Int>): Int {
            var pos = -1
            var last = Int.MAX_VALUE
            dist.forEachIndexed {idx, node ->
                if(node < last && node > -1 && Q.contains(idx)) {
                    pos = idx
                    last = node
                }
            }
            return pos
        }

    }

}