fun main() {

    fun part1(input: List<Pair<String,Int>> ): Int {
        var (horizontal, depth) = Pair(0,0)
        for ((action,num) in input) {
            when(action) {
                "forward" -> horizontal += num
                "up" -> depth -= num
                "down" -> depth += num
            }
        }
        return horizontal * depth
    }

    fun part2(input: List<Pair<String,Int>> ): Int {
        var (horizontal, depth, aim) = Triple(0,0,0)
        for ((action,num) in input) {
            when(action) {
                "up" -> aim -= num
                "down" -> aim += num
                "forward" -> {
                    horizontal += num
                    depth += (aim * num)
                }
            }
        }
        return horizontal * depth
    }

    val input = loadD2Input("Day02_test")
    val p1 = part1(input)
    val p2 = part2(input)

    println(p1)
    println(p2)
    check(p1 == 1990000)
    check(p2 == 1975421260)
}
