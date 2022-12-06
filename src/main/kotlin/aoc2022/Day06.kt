package aoc2022


import readInputAsString

fun main() {

	fun String.hasUniqueCharacters() = this.toSet().size == length

    fun part1(input: String) {
	    val distinctRequired = 4
		val marker = input.windowed(distinctRequired, 1)
			.indexOfFirst {
				it.hasUniqueCharacters()
			} + distinctRequired
	    println(marker)
    }

    fun part2(input: String) {
	    val distinctRequired = 14
	    val marker = input.windowed(distinctRequired, 1)
			.indexOfFirst {
			    it.hasUniqueCharacters()
		    } + distinctRequired
	    println(marker)
    }

    val input = readInputAsString("aoc2022/Day06")
	part1(input)
	part2(input)
}