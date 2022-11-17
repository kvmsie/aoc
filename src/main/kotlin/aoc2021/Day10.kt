package aoc2021

import readInput

fun main() {

    val correctBrackets = listOf("[]", "()", "{}", "<>")
    val incorrectBrackets = listOf(
        correctBrackets[0][0].toString() + correctBrackets[1][1].toString(), // [)
        correctBrackets[0][0].toString() + correctBrackets[2][1].toString(), // [}
        correctBrackets[0][0].toString() + correctBrackets[3][1].toString(), // [>

        correctBrackets[1][0].toString() + correctBrackets[0][1].toString(), // (]
        correctBrackets[1][0].toString() + correctBrackets[2][1].toString(), // (}
        correctBrackets[1][0].toString() + correctBrackets[3][1].toString(), // (>

        correctBrackets[2][0].toString() + correctBrackets[0][1].toString(), // {]
        correctBrackets[2][0].toString() + correctBrackets[1][1].toString(), // {)
        correctBrackets[2][0].toString() + correctBrackets[3][1].toString(), // {>

        correctBrackets[3][0].toString() + correctBrackets[0][1].toString(), // {]
        correctBrackets[3][0].toString() + correctBrackets[1][1].toString(), // {)
        correctBrackets[3][0].toString() + correctBrackets[2][1].toString(), // {>
    )

    fun String.removeAll(conditions: List<String>) : String {
        var newString = this

        conditions.forEach {
            if (contains(it)) {
                newString = newString.replace(it, "")
                return newString.removeAll(conditions)
            }
        }

        return newString
    }

    fun String.containsAny(conditions: List<String>) : Boolean {
        conditions.forEach {
            if (contains(it))
                return true
        }

        return false
    }

    fun part1(input: List<String>) {
        val bracketValues = mapOf(
            ')' to 3,
            ']' to 57,
            '}' to 1197,
            '>' to 25137
        )

        val result = input.sumOf {
            var line = it

            while (line.containsAny(correctBrackets)) {
                line = line.removeAll(correctBrackets)
            }

            if (line.containsAny(incorrectBrackets)) {
                val char = line.windowed(2, 1).first { pair ->
                    pair in incorrectBrackets
                }[1]
                bracketValues[char]!!

            } else {
                0
            }
        }

        println(result)
    }

    fun part2(input: List<String>) {
        val scores = mapOf(
            '(' to 1L,
            '[' to 2L,
            '{' to 3L,
            '<' to 4L
        )

        val result = input.map {
            var line = it

            while (line.containsAny(correctBrackets)) {
                line = line.removeAll(correctBrackets)
            }

            line
        }.filterNot { line ->
            line.containsAny(incorrectBrackets)
        }.map { str ->
            str.reversed().map {
                scores[it]!!
            }.fold(0L) { result, value ->
                result * 5 + value
            }
        }

        println(result.sorted()[result.size / 2])
    }

    val input = readInput("aoc2021/Day10")
    part1(input)
    part2(input)
}

