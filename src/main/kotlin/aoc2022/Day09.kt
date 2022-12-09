package aoc2022


import readInput
import kotlin.math.abs

fun main() {
	data class Instruction(
		val direction: Char,
		val steps: Int
	)

	fun moveHead(headPosition: Pair<Int, Int>, direction: Char) : Pair<Int, Int> {
		return when (direction) {
			'R'  -> headPosition.first + 1 to headPosition.second
			'L'  -> headPosition.first - 1 to headPosition.second
			'U'  -> headPosition.first to headPosition.second + 1
			'D'  -> headPosition.first to headPosition.second - 1
			else -> headPosition.first to headPosition.second
		}
	}

	fun moveTail(headPosition: Pair<Int, Int>, tailPosition: Pair<Int, Int>) : Pair<Int, Int> {
		return if (abs(headPosition.first - tailPosition.first) <= 1 && abs(headPosition.second - tailPosition.second) <= 1) {
			tailPosition

		} else {
			val x = headPosition.first.compareTo(tailPosition.first)
			val y = headPosition.second.compareTo(tailPosition.second)
			tailPosition.first + (1 * x) to tailPosition.second + (1 * y)
		}
	}

    fun part1(input: List<String>) {
	    val instructions = input.map { line ->
		    Instruction(
			    direction = line[0],
			    steps = line.substringAfter(" ").toInt()
		    )
	    }

	    var headPosition = 0 to 0
	    var tailPosition = 0 to 0
	    val visitedPoints = mutableSetOf(tailPosition)

	    instructions.forEach { instruction ->
		    repeat(instruction.steps) {
				headPosition = moveHead(headPosition, instruction.direction)
			    tailPosition = moveTail(headPosition, tailPosition)
			    visitedPoints.add(tailPosition)
		    }
	    }
	    println(visitedPoints.size)
    }

    fun part2(input: List<String>) {
	    val instructions = input.map { line ->
		    Instruction(
			    direction = line[0],
			    steps = line.substringAfter(" ").toInt()
		    )
	    }

	    val tail = MutableList(10) { 0 to 0 }
	    val visitedPoints = mutableSetOf(tail.last())

	    instructions.forEach { instruction ->
		    repeat(instruction.steps) {
			    tail[0] = moveHead(tail[0], instruction.direction)
			    for (i in 0 until tail.lastIndex) {
					tail[i + 1] = moveTail(tail[i], tail[i + 1])
			    }
			    visitedPoints.add(tail[9])
		    }
	    }
	    println(visitedPoints.size)
    }

    val input = readInput("aoc2022/Day09")
	part1(input)
	part2(input)
}