package aoc2022


import readInput

fun main() {

	fun createStacks(input: List<String>) : List<ArrayDeque<Char>> {
		val crates = input.takeWhile {
			it.isNotEmpty()
		}.reversed()
			.drop(1)

		val stacks = List(9) { index ->
			val queue = ArrayDeque<Char>()
			for (i in crates.indices) {
				val letter = crates[i].getOrNull(4 * index + 1)
				if (letter != null && letter.isLetter())
					queue.add(letter)
				else
					break
			}
			queue
		}

		return stacks
	}

    fun part1(input: List<String>) {
	    val stacks = createStacks(input)
	    val match = Regex("move (\\d+) from (\\d+) to (\\d+)")
	    input.subList(input.indexOfFirst { it.isEmpty() } + 1, input.size)
		    .forEach {
			    val (move, from, to) = match.find(it)!!.destructured
			    repeat(move.toInt()) {
				    val pop = stacks[from.toInt() - 1].removeLast()
				    stacks[to.toInt() - 1].add(pop)
			    }
		    }

	    stacks.forEach {
			print(it.last())
	    }
    }

    fun part2(input: List<String>) {
	    val stacks = createStacks(input)
	    val match = Regex("move (\\d+) from (\\d+) to (\\d+)")
	    input.subList(input.indexOfFirst { it.isEmpty() } + 1, input.size)
		    .forEach {
			    val (move, from, to) = match.find(it)!!.destructured
			    val pop = stacks[from.toInt() - 1].takeLast(move.toInt())
			    repeat(move.toInt()) {
				    stacks[from.toInt() - 1].removeLast()
			    }
			    stacks[to.toInt() - 1].addAll(pop)
		    }

	    stacks.forEach {
		    print(it.last())
	    }
    }

    val input = readInput("aoc2022/Day05")
    part1(input)
	println()
	part2(input)
}