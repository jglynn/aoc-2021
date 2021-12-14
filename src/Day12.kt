
fun main() {

    // Uppercase nodes can be visited more than once
    // All other nodes only once
    fun canVisitPart1(visitedNodes: List<String>, node: String): Boolean =
        node.isUpperCase() or (node !in visitedNodes)

    // Uppercase nodes can be visited more than once
    // Only one lowercase node can be visited twice
    // start cannot be revisited
    fun canVisitPart2(visitedNodes: List<String>, node: String): Boolean =
        when {
            node.isUpperCase() -> true
            node !in visitedNodes -> true
            node == "start" -> false
            else -> visitedNodes
                .filterNot { it.isUpperCase() }
                .groupingBy { it }
                .eachCount()
                .none { it.value == 2 }
        }

    fun solve(input: List<String>, canVisit: (nodes: List<String>, node: String) -> Boolean ): Int {
        val graph = loadD12Input(input)
        val dfs = DFS(canVisit)
        dfs.enumerate(graph, "start", "end")
        return dfs.allPaths.size
    }

    fun part1(input: List<String> ): Int {
        return solve(input, ::canVisitPart1)
    }

    fun part2(input: List<String> ): Int {
        return solve(input, ::canVisitPart2)
    }

    val input1 = readInput("Day12_test1")
    val input2 = readInput("Day12_test2")
    val input3 = readInput("Day12_test3")
    val input = readInput("Day12_test")

    val p1test1 = part1(input1)
    check(p1test1 == 10)

    val p1test2 = part1(input2)
    check(p1test2 == 19)

    val p1test3 = part1(input3)
    check(p1test3 == 226)

    val part1Result = part1(input)
    println("Part1 Results = $part1Result")

    val p2test1 = part2(input1)
    check(p2test1 == 36)

    val p2test2 = part2(input2)
    check(p2test2 == 103)

    val p2test3 = part2(input3)
    check(p2test3 == 3509)

    val part2Result = part2(input)
    println("Part2 Results = $part2Result")
}
