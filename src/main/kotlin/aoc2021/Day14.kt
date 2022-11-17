package aoc2021

import readInput

fun main() {

    fun part(input: List<String>, steps: Int) {
        val template = input.first()
        val instructions = input.takeLastWhile { it.isNotEmpty() }
            .associate {
                val values = it.split(" -> ")
                values[0] to values[1].single()
            }
        var pairCounts = mutableMapOf<String, Long>()
        val scores = mutableMapOf<Char, Long>()

        template.windowed(2).forEach {
            pairCounts[it] = pairCounts.getOrDefault(it, 0) + 1
        }

        repeat(steps) {
            val stepPairs = mutableMapOf<String, Long>()

            for (pair in pairCounts.keys) {
                val newThree = StringBuilder()
                newThree.append(pair[0], instructions[pair], pair[1])

                newThree.windowed(2).forEach {
                    stepPairs[it] = stepPairs.getOrDefault(it, 0) + pairCounts[pair]!!
                }
            }

            pairCounts = stepPairs
        }

        pairCounts.forEach { pair ->
            val letter = pair.key[1]
            scores[letter] = scores.getOrDefault(letter, 0L) + pair.value
        }

        val firstLetter = template.first()
        scores[firstLetter] = scores.getOrDefault(firstLetter, 0L) + 1

        println(scores.maxOf { it.value } - scores.minOf { it.value })
    }

    val input = readInput("aoc2021/Day14")
    part(input, 10)
    part(input, 40)
}