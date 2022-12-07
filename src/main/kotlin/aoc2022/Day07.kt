package aoc2022


import readInput

fun main() {

    fun part1(input: List<String>) {
	    val stack = ArrayDeque<String>()
	    val sizes = mutableMapOf<String, Long>()

	    input.forEach { line ->
			if (line.startsWith("$ cd ..")) {
				stack.removeLast()

			} else if (line.startsWith("$ cd ")) {
				val dir = line.substringAfter("$ cd ")
				val name =
					if (dir == "/") "/"
					else "${stack.last()}$dir/"
				stack.add(name)

			} else if (line.first().isDigit()){
				for (dir in stack) {
					val weight = line.substringBefore(" ").toLong()
					val currentWeight = sizes.getOrElse(dir) { 0 }
					sizes[dir] = currentWeight + weight
				}
			}
	    }

	    val sum = sizes.values.filter {
			    it < 100000
			}.sum()
	    println(sum)
    }

    fun part2(input: List<String>) {
	    val stack = ArrayDeque<String>()
	    val dirs = mutableMapOf<String, Long>()

	    input.forEach { line ->
		    if (line.startsWith("$ cd ..")) {
			    stack.removeLast()

		    } else if (line.startsWith("$ cd ")) {
			    val dirName = line.substringAfter("$ cd ")
			    val name =
				    if (dirName == "/") "/"
				    else "${stack.last()}$dirName/"
			    stack.add(name)

		    } else if (line.first().isDigit()) {
			    for (dir in stack) {
				    val weight = line.substringBefore(" ").toLong()
				    val currentWeight = dirs.getOrElse(dir) { 0 }
				    dirs[dir] = currentWeight + weight
			    }
		    }
	    }

	    val requiredSpace = 30000000 - (70000000 - dirs["/"]!!)
	    val smallestDirSize = dirs.values
			.sorted()
			.first {
				it > requiredSpace
		    }
	    println(smallestDirSize)
    }

    val input = readInput("aoc2022/Day07")
	part1(input)
	part2(input)
}