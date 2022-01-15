package org.jglynn.aoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 14")
class Day14Test {

    // Arrange
    private val input = Utils.resourceAsListOfString("Day14_sample.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day14(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(1_588)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day14(Utils.resourceAsListOfString("Day14_test.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(3_306)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        // @Test
        fun `Matches example`() {
            // Act
            val answer = Day14(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(2_188_189_693_529)
        }

       // @Test
        fun `Actual answer`() {
            // Act
            val answer = Day14(Utils.resourceAsListOfString("Day14_test.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(3_760_312_702_877)
        }
    }
}