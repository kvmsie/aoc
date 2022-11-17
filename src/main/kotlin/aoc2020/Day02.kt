package aoc2020

import readInput

fun main() {
    fun part1(input: List<String>) {
        val cnt = input.count { row ->
            val criteria = row.substringBefore(':').split(" ")
            val password = row.substringAfter(": ")
            val occurences = Pair(criteria[0].split("-")[0].toInt(), criteria[0].split("-")[1].toInt())
            val letter = criteria.takeLast(1).first()

            password.count { it.toString() == letter } in occurences.first .. occurences.second
        }

        println(cnt)
    }

    fun part2(input: List<String>) {
        val cnt = input.count { row ->
            val criteria = row.substringBefore(':').split(" ")
            val password = row.substringAfter(": ")
            val occurences = Pair(criteria[0].split("-")[0].toInt(), criteria[0].split("-")[1].toInt())
            val letter = criteria.takeLast(1).first()

            (password[occurences.first - 1].toString() == letter).xor(password[occurences.second - 1].toString() == letter)
        }

        println(cnt)
    }

    val input = readInput("aoc2020/Day02")
    part1(input)
    part2(input)
}