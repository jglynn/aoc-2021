package org.jglynn.aoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 12")
class Day12Test {

    // Arrange
    private val input = """
        start-A
        start-b
        A-c
        A-b
        b-d
        A-end
        b-end
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day12(Utils.loadAsGraph(input)).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(10)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day12(Utils.loadAsGraph("Day12_test.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(3_230)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day12(Utils.loadAsGraph(input)).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(36)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day12(Utils.loadAsGraph("Day12_test.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(83_475)
        }
    }
}