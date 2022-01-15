package org.jglynn.aoc

import java.math.RoundingMode

class Day03(private val input: List<String>)  {

    fun solvePart1(): Int {

        val binMap = mutableMapOf<Int,MutableList<Int>>()

        // assemble bits into Map of lists keyed by their position
        for(bin in input) {
            for (i in bin.indices) {
                binMap.getOrPut(i) { mutableListOf() }.add(bin[i].toString().trim().toInt())
            }
        }

        // find significant bits per position as "gamma"
        val gamma_list = mutableListOf<Int>()
        for(vals in binMap.values) {
            val common_significant_bit = vals.sum().toBigDecimal().divide(vals.size.toBigDecimal(), RoundingMode.HALF_EVEN ).toInt()
            gamma_list.add(common_significant_bit)
        }

        // convert gamma binary to decimal value
        val binary_gamma = gamma_list.joinToString("")
        val gamma = Integer.parseInt(binary_gamma, 2)
        println("gamma = $gamma")

        // convert gamma decimal into epsilon (inverted bits masked to match length of source)
        val mask = 1.shl(binary_gamma.length) - 1
        val epsilon = gamma.inv() and mask
        println("epsilon = $epsilon")
        val binary_ep = Integer.toBinaryString(epsilon).padStart(binary_gamma.length, '0')

        println(binary_gamma)
        println(binary_ep)

        // calculate power
        return gamma * epsilon

    }

}