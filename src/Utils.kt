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
    return readInput(name).map() { Pair(it.split(" ")[0], it.split(" ")[1].toInt()) }
}
/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
