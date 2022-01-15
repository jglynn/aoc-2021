
package org.jglynn.aoc

import org.assertj.core.api.Assertions.assertThat
import org.jglynn.aoc.Utils.loadAsListOfInt
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 1")
class Day01Test {

    // Arrange
    private val input = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day01(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(7)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day01(loadAsListOfInt("Day01_test.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(1_529)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day01(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(5)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day01(loadAsListOfInt("Day01_test.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(1_567)
        }
    }

    @Nested
    @DisplayName("Part 2 - Windowed")
    inner class Part2Win {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day01(input).solvePart2_windowed()

            // Assert
            assertThat(answer).isEqualTo(5)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day01(loadAsListOfInt("Day01_test.txt")).solvePart2_windowed()

            // Assert
            assertThat(answer).isEqualTo(1_567)
        }
    }
}