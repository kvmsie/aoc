package aoc2021

import readInput

fun main() {

    data class Fold(
        val axis: String,
        val position: Int
    )

    fun part(input: List<String>) {
        val points = input.takeWhile { it.isNotEmpty() }
            .map {
                val directions = it.split(",")
                directions[0].toInt() to directions[1].toInt()
            }

        val folds = input.takeLastWhile { it.isNotEmpty() }
            .map {
                val fold = it.substringAfter("fold along ").split("=")
                Fold(fold[0], fold[1].toInt())
            }

        val ySize = folds.first { it.axis == "y" }.position * 2
        val xSize = folds.first { it.axis == "x" }.position * 2

        var board = Array(ySize + 1) {
            Array(xSize + 1) { '.' }
        }

        points.forEach {
            board[it.second][it.first] = '#'
        }

        folds.forEachIndexed { index, fold ->
            when (fold.axis) {
                "x" -> {
                    for (y in board.indices) {
                        val reversed = board[y].takeLast(fold.position).reversed()

                        for (x in reversed.indices) {
                            if (reversed[x] == '#') {
                                board[y][x] = reversed[x]
                            }
                        }
                    }

                    board = board.map {
                        it.take(fold.position).toTypedArray()
                    }.toTypedArray()
                }
                "y" -> {
                    val reversed = board.takeLast(fold.position).reversed()

                    for (y in reversed.indices) {
                        for (x in reversed[y].indices) {
                            if (reversed[y][x] == '#') {
                                board[y][x] = reversed[y][x]
                            }
                        }
                    }

                    board = board.take(fold.position).toTypedArray()
                }
            }

            if (index == 0) {
                val cnt = board.flatten().count { it == '#' }
                println("Part1: $cnt")
            }
        }

        println("Part2:")
        board.forEach {
            it.forEach { print(it) }
            println()
        }


    }

    val input = readInput("aoc2021/Day13")
    part(input)
}

