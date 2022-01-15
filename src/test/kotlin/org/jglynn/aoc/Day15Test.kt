package org.jglynn.aoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 15")
class Day15Test {

    // Arrange
    private val input = """
        1163751742
        1381373672
        2136511328
        3694931569
        7463417111
        1319128137
        1359912421
        3125421639
        1293138521
        2311944581
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day15(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(40)
        }

        //@Test
        fun `Actual answer`() {
            // Act
            val answer = Day15(Utils.resourceAsListOfString("Day15_test.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(707)
        }
    }

}