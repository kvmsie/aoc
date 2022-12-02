package aoc2022


import readInput

fun main() {

    fun calculateScore(opponent: Char, me: Char) =
        when (opponent) {
            'A' -> { /* rock */
                when (me) {
                    'X' -> 4 /* rock (draw) */
                    'Y' -> 8 /* paper (win) */
                    'Z' -> 3 /* scissors (lose) */
                    else -> 0
                }
            }
            'B' -> { /* paper */
                when (me) {
                    'X' -> 1 /* rock (lose) */
                    'Y' -> 5 /* paper (draw) */
                    'Z' -> 9 /* scissors (win) */
                    else -> 0
                }
            }
            'C' -> { /* scissors */
                when (me) {
                    'X' -> 7 /* rock (win) */
                    'Y' -> 2 /* paper (lose) */
                    'Z' -> 6 /* scissors (draw) */
                    else -> 0
                }
            }
            else -> 0
        }

    fun part1(input: List<String>) {
        val score = input.map {
            it[0] to it[2]
        }.fold(0) { acc, pair ->
            acc + calculateScore(pair.first, pair.second)
        }
        println(score)
    }

    fun part2(input: List<String>) {
        val lose = mapOf('A' to 'Z', 'B' to 'X', 'C' to 'Y')
        val draw = mapOf('A' to 'X', 'B' to 'Y', 'C' to 'Z')
        val win  = mapOf('A' to 'Y', 'B' to 'Z', 'C' to 'X')

        val score = input.map {
            it[0] to it[2]
        }.fold(0) { acc, pair ->
            val me = when (pair.second) {
                'X' -> lose.getValue(pair.first)
                'Y' -> draw.getValue(pair.first)
                'Z' -> win.getValue(pair.first)
                else -> throw IllegalArgumentException("Unknown instruction: ${pair.second}")
            }
            acc + calculateScore(pair.first, me)
        }
        println(score)
    }

    val input = readInput("aoc2022/Day02")
    part1(input)
    part2(input)

}