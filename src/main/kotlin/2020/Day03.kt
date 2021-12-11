package `2020`

import readInput

fun main() {
    fun part1(input: List<String>) {
        val cnt = input.foldIndexed(0) { index, result, value ->
            if (value[(index * 3) % value.length] == '#') result + 1
            else result
        }

        println(cnt)
    }

    fun part2(input: List<String>) {
        val movements = listOf(
            1 to 1,
            3 to 1,
            5 to 1,
            7 to 1,
            1 to 2
        )

        val cnt = movements.map {
            var idx = 0

            input.foldIndexed(0L) { index, result, value ->
                if (index % it.second == 0) {
                    if (value[(idx++ * it.first) % value.length] == '#') result + 1
                    else result
                } else {
                    result
                }
            }
        }.fold(1L) { result, value ->
            result * value
        }

        println(cnt)
    }

    val input = readInput("2020\\Day03")
    part1(input)
    part2(input)
}