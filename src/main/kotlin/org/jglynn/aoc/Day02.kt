package org.jglynn.aoc

class Day02(private val input: List<Pair<String,Int>>) {

    fun solvePart1(): Int {
        var (horizontal, depth) = Pair(0,0)
        for ((action,num) in input) {
            when(action) {
                "forward" -> horizontal += num
                "up" -> depth -= num
                "down" -> depth += num
            }
        }
        return horizontal * depth
    }

    fun solvePart2(): Int {
        var (horizontal, depth, aim) = Triple(0,0,0)
        for ((action,num) in input) {
            when(action) {
                "up" -> aim -= num
                "down" -> aim += num
                "forward" -> {
                    horizontal += num
                    depth += (aim * num)
                }
            }
        }
        return horizontal * depth
    }
}