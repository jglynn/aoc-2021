import java.math.RoundingMode

fun main() {

    fun part1(input: List<String> ): Int {

        var binMap = mutableMapOf<Int,MutableList<Int>>()

        // assemble bits into Map of lists keyed by their position
        for(bin in input) {
            for (i in bin.indices) {
                binMap.getOrPut(i) { mutableListOf() }.add(bin[i].toString().trim().toInt())
            }
        }

        // find significant bits per position as "gamma"
        var gamma_list = mutableListOf<Int>()
        for((pos,vals) in binMap) {
            var common_significant_bit = vals.sum().toBigDecimal().divide(vals.size.toBigDecimal(), RoundingMode.HALF_EVEN ).toInt()
            println("$pos = $common_significant_bit")
            gamma_list.add(common_significant_bit)
        }

        // convert gamma binary to decimal value
        var binary_gamma = gamma_list.joinToString("")
        var gamma = Integer.parseInt(binary_gamma, 2)
        println("gamma = $gamma")

        // convert gamma decimal into epsilon (inverted bits masked to match length of source)
        val mask = 1.shl(binary_gamma.length) - 1
        var epsilon = gamma.inv() and mask
        println("epsilon = $epsilon")
        var binary_ep = Integer.toBinaryString(epsilon).padStart(binary_gamma.length, '0')

        // calculate power
        var power = gamma * epsilon
        println("power = $power")

        println("$binary_gamma")
        println("$binary_ep")

        return power
    }

    fun part2(input: List<String> ): Int {
        return input.size
    }

    val input = readInput("Day03_test")
    val p1 = part1(input)
    val p2 = part2(input)

    println(p1)
    println(p2)
    check(p1 == 845186)
    check(p2 == 1000)
}
