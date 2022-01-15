package org.jglynn.aoc

import java.io.File
import java.net.URI

/**
 * Reads lines from the given input txt file.
 */
internal object Utils {

    fun resourceAsListOfString(fileName: String): List<String> =
        File(fileName.toURI()).readLines()

    fun loadAsListOfInt(name: String): List<Int> {
        return resourceAsListOfString(name).map { it.toInt() }
    }

    fun loadAsListOfStringIntPair(name: String): List<Pair<String,Int>> {
        return resourceAsListOfString(name).map() {
            val (direction, value) = it.split(" ")
            Pair(direction, value.toInt())
        }
    }

    fun loadAsGraph(fileName: String): Graph<String> =
        loadAsGraph(resourceAsListOfString(fileName))

    fun loadAsGraph(input: List<String>): Graph<String> {
        val graph = Graph<String>()
        input.map {
            val (src, dest) = it.split("-")
            graph.addEdge(src, dest)
        }
        return graph
    }

    private fun String.toURI(): URI =
        Utils.javaClass.classLoader.getResource(this)?.toURI() ?: throw IllegalArgumentException("Cannot find Resource: $this")
 }