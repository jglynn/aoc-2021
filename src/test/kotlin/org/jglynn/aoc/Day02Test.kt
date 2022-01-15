/*
 * Copyright (c) 2021 by Todd Ginsberg
 */

package org.jglynn.aoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 2")
class Day02Test {

    private val input = listOf<Pair<String,Int>>(
        Pair("forward",5),
        Pair("down",5),
        Pair("forward",8),
        Pair("up",3),
        Pair("down",8),
        Pair("forward",2)
    )

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day02(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(150)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day02(Utils.loadAsListOfStringIntPair("Day02_test.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(1_990_000)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day02(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(900)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day02(Utils.loadAsListOfStringIntPair("Day02_test.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(1_975_421_260)
        }
    }
}