import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

fun loadD1Input(name: String): List<Int> {
    return readInput(name).map { it.toInt() }
}

fun loadD2Input(name: String): List<Pair<String,Int>> {
    return readInput(name).map() {
        val (name, value) = it.split(" ")
        Pair(name, value.toInt())
    }
}

fun loadD12Input(input: List<String>): Graph<String> {
    var graph = Graph<String>()
    input.map {
        val (src, dest) = it.split("-")
        graph.addEdge(src, dest)
    }
    return graph
}

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
