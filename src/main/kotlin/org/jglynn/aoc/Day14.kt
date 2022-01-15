package org.jglynn.aoc

class Day14(private val input: List<String>) {

    fun solvePart1(): Int {
        var chain = input.first()
        val polyMap = parseMappings(input)
        println(polyMap)

        val templateCounts = parseTemplateCounts(chain)
        println(templateCounts)

        repeat(10) {
            chain = solve(chain, polyMap)
            //println("${chain.length}")
        }

        val counts = chain
            .toList()
            .groupingBy { it.toString() }
            .eachCount()

        return (counts.maxByOrNull { it.value }?.value ?: 0) - (counts.minByOrNull { it.value }?.value ?: 0)

    }

    fun solvePart2(): Int {
        return 0
    }

    private fun parseMappings(input: List<String>): Map<String, String> =
        input
            .drop(2)
            .associate {
                it.split(" -> ").let { (k, v) -> k to v }
            }

    private fun parseTemplate(template: String): List<List<Char>> {
        return template.toList().windowed(2, 1)
    }

    private fun parseTemplateCounts(template: String): Map<String, Long> {
        return template
            .windowed(2, 1)
            .groupingBy { it }
            .eachCount()
            .mapValues { it.value.toLong() }
    }

    private fun solve(polyTemplate: String, mappings: Map<String, String>): String {
        val templateList = parseTemplate(polyTemplate)
        //println(templateList)
        val newPoly = templateList
            .map { it.first() + mappings.getOrDefault(it.joinToString(""), "") }
            .toMutableList()
        newPoly.add(templateList.last().last().toString())
        return newPoly.joinToString("")
    }

}