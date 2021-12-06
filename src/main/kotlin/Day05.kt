import kotlin.math.max
import kotlin.math.min

fun main() {

    data class Point(val x: Int, val y: Int)

    fun part1(input: List<String>) {
        val vents = mutableMapOf<String, Int>()

        input.map { row ->
            val first = row.substringBefore(" -> ")
            val second = row.substringAfter(" -> ")

            Pair(
                Point(first.substringBefore(",").toInt(), first.substringAfter(",").toInt()),
                Point(second.substringBefore(",").toInt(), second.substringAfter(",").toInt())
            )
        }.forEach {
            if (it.first.x == it.second.x) {
                for (i in min(it.first.y, it.second.y) .. max(it.first.y, it.second.y)) {
                    vents["${it.first.x},$i"] = vents.getOrDefault("${it.second.x},$i", 0) + 1
                }
            } else if (it.first.y == it.second.y) {
                for (i in min(it.first.x, it.second.x) .. max(it.first.x, it.second.x)) {
                    vents["$i,${it.first.y}"] = vents.getOrDefault("$i,${it.first.y}", 0) + 1
                }
            }
        }

        println(vents.count { it.value >= 2 })
    }

    fun part2(input: List<String>) {
        val vents = mutableMapOf<String, Int>()

        input.map { row ->
            val first = row.substringBefore(" -> ")
            val second = row.substringAfter(" -> ")

            Pair(
                Point(first.substringBefore(",").toInt(), first.substringAfter(",").toInt()),
                Point(second.substringBefore(",").toInt(), second.substringAfter(",").toInt())
            )
        }.forEach {
            if (it.first.x == it.second.x) {
                for (i in min(it.first.y, it.second.y) .. max(it.first.y, it.second.y)) {
                    vents["${it.first.x},$i"] = vents.getOrDefault("${it.second.x},$i", 0) + 1
                }
            } else if (it.first.y == it.second.y) {
                for (i in min(it.first.x, it.second.x) .. max(it.first.x, it.second.x)) {
                    vents["$i,${it.first.y}"] = vents.getOrDefault("$i,${it.first.y}", 0) + 1
                }
            } else if (
                max(it.first.y, it.second.y) - min(it.first.y, it.second.y) == // Diagonal
                max(it.first.x, it.second.x) - min(it.first.x, it.second.x)
            ) {
                // fixme sort by x in input.map {}
                val lower: Point
                val higher: Point

                if (it.first.x < it.second.x) {
                    lower = it.first
                    higher = it.second
                } else {
                    lower = it.second
                    higher = it.first
                }

                val way =
                    if (lower.y < higher.y) 1
                    else -1

                for (i in 0 .. higher.x - lower.x) {
                    val key = "${lower.x + i},${lower.y + (i * way)}"
                    vents[key] = vents.getOrDefault(key, 0) + 1
                }
            }
        }

        println(vents.count { it.value >= 2 })
    }

    val input = readInput("Day05")
    part1(input)
    part2(input)
}