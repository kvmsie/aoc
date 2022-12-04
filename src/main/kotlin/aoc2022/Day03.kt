package aoc2022


import readInput

fun main() {

    fun part1(input: List<String>) {
        val result = input.sumOf {
                val middleIndex = it.length / 2
                val compartments = it.substring(0, middleIndex).toSet() to it.substring(middleIndex).toSet()
                val common = compartments.first
                    .intersect(compartments.second)
                    .first()
		        if (common.isLowerCase())
			        common.code - 'a'.code + 1
                else
					common.code - 'A'.code + 27
            }
        println(result)
    }

    fun part2(input: List<String>) {
        val result = input.windowed(3, 3)
	        .sumOf { group ->
				val common = group.map { elf ->
					elf.toSet()
				}.reduce { acc, elf ->
					acc.intersect(elf)
				}.first()
		        if (common.isLowerCase())
			        common.code - 'a'.code + 1
		        else
			        common.code - 'A'.code + 27
	        }
	    println(result)
    }

    val input = readInput("aoc2022/Day03")
    part1(input)
	part2(input)
}