package org.jglynn.aoc

class Day01(private val input: List<Int>) {

    fun solvePart1(): Int =
        input.filterIndexed { i, v -> (i != 0) && (v > input[i-1])}.size

    fun solvePart2(): Int {
        var count = 0
        for(i in 3 until input.size) {
            val window1 = input[i-3] + input[i-2] + input[i-1]
            val window2 = input[i-2] + input[i-1] + input[i]
            if (window2 > window1) count++
        }
        return count
    }

    fun solvePart2_windowed(): Int =
        input
            .windowed(3, 1) { it.sum() }
            .zipWithNext()
            .count { it.first < it.second }
}
