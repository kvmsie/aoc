package aoc2022


import readInput

fun main() {

    fun part1(input: List<String>) {
		val overlaps = input.count { pairs ->
            val firstSection = pairs.substringBefore(',')
            val firstRange = firstSection.substringBefore('-').toInt() .. firstSection.substringAfter('-').toInt()
            val secondSection = pairs.substringAfter(',')
            val secondRange = secondSection.substringBefore('-').toInt() .. secondSection.substringAfter('-').toInt()
            (firstRange.contains(secondRange.first) && firstRange.contains(secondRange.last)) ||
                (secondRange.contains(firstRange.first) && secondRange.contains(firstRange.last))
        }
        println(overlaps)
    }

    fun part2(input: List<String>) {
        val overlaps = input.count { pairs ->
            val firstSection = pairs.substringBefore(',')
            val firstRange = firstSection.substringBefore('-').toInt() .. firstSection.substringAfter('-').toInt()
            val secondSection = pairs.substringAfter(',')
            val secondRange = secondSection.substringBefore('-').toInt() .. secondSection.substringAfter('-').toInt()
            firstRange.contains(secondRange.first) || firstRange.contains(secondRange.last) ||
                secondRange.contains(firstRange.first) || secondRange.contains(firstRange.last)
        }
        println(overlaps)
    }

    val input = readInput("aoc2022/Day04")
    part1(input)
	part2(input)
}