fun main() {
    fun part1(input: List<Int>): Int {
        var deeperReadings = input.filterIndexed { i, v -> (i != 0) && (v > input.get(i-1))}
        return deeperReadings.size
    }

    fun part2(input: List<Int>): Int {
        var count = 0
        for(i in 3 until input.size) {
            val window1 = input[i-3] + input[i-2] + input[i-1]
            val window2 = input[i-2] + input[i-1] + input[i]
            if (window2 > window1) count++
        }
        return count
    }

    val input = loadD1Input("Day01_test")
    println(part1(input))
    println(part2(input))
}
