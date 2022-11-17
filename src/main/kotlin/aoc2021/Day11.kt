package aoc2021

import readInput

fun main() {

    data class Octopus(
        var energy: Int,
        var hasFlashed: Boolean = false
    ) {
//        fun flash() {
//            if (!hasFlashed) {
//                hasFlashed = true
//            }
//
//            energy =
//                if (energy == 9) 0
//                else energy++
//        }
    }

    fun flash(board: Array<Array<Octopus>>, y: Int, x: Int, flashCnt: Int = 0) : Int {
        var currentFlashCnt = flashCnt

        val octopus = board[y][x]

        octopus.energy = 0

        if (!octopus.hasFlashed) {
            octopus.hasFlashed = true
            currentFlashCnt += 1

            for (flashY in y - 1..y + 1) {
                for (flashX in x - 1..x + 1) {
                    if (flashY == y && flashX == x) continue
                    val nextOctopus = board.getOrNull(flashY)?.getOrNull(flashX)
                    nextOctopus?.let {
                        if (!it.hasFlashed) {
                            if (it.energy == 9) {
                                currentFlashCnt = flash(board, flashY, flashX, currentFlashCnt)
                            } else {
                                it.energy++
                            }
                        }
                    }
                }
            }
        }

        return currentFlashCnt
    }


    fun part1(input: List<String>) {
        val board = input.map {
            it.toCharArray().map {
                Octopus(Character.getNumericValue(it))
            }.toTypedArray()
        }.toTypedArray()

        var flashCnt = 0

        repeat(100) {
            for (y in board.indices) { // row
                for (x in board[y].indices) { // column
                    if (!board[y][x].hasFlashed) {
                        if (board[y][x].energy == 9) {
                            flashCnt = flash(board, y, x, flashCnt)
                        } else {
                            board[y][x].energy++
                        }
                    }
                }
            }

            for (y in board.indices) { // row
                for (x in board[y].indices) { // column
                    board[y][x].hasFlashed = false
                }
            }
        }

        println(flashCnt)
    }

    fun part2(input: List<String>) {
        val board = input.map {
            it.toCharArray().map {
                Octopus(Character.getNumericValue(it))
            }.toTypedArray()
        }.toTypedArray()

        var stepCnt = 1

        while (true) {
            for (y in board.indices) { // row
                for (x in board[y].indices) { // column
                    if (!board[y][x].hasFlashed) {
                        if (board[y][x].energy == 9) {
                            flash(board, y, x)
                        } else {
                            board[y][x].energy++
                        }
                    }
                }
            }

            if (board.all { it.all { it.hasFlashed } }) {
                break
            }

            for (y in board.indices) { // row
                for (x in board[y].indices) { // column
                    board[y][x].hasFlashed = false
                }
            }

            stepCnt++
        }
        println(stepCnt)
    }

    val input = readInput("aoc2021/Day11")
    part1(input)
    part2(input)
}

