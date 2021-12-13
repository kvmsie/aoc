package `2021`

import readInput

fun main() {

    fun String.isSmallCave() = all { it.isLowerCase() }

    fun generatePaths(input: List<String>) =
        input.asSequence()
            .map {
                val path = it.split("-")
                listOf(Pair(path[0], path[1]), Pair(path[1], path[0]))
            }
            .flatten()
            .filterNot { it.second == "start" || it.first == "end" }
            .distinct()
            .groupBy { it.first }
            .map {
                it.key to it.value.map {
                    it.second
                }
            }.toMap()

    fun part1(input: List<String>) {
        val paths = generatePaths(input)

        fun navigate(source: String, pathCnt: Int, visited: MutableList<String>) : Int {
            var currentCnt = pathCnt

            paths[source]!!.forEach { destination ->

                if (destination in visited) {
                    return@forEach
                }

                when (destination) {
                    "end" -> {
                        currentCnt++
                    }
                    else -> {
                        if (destination.isSmallCave()) {
                            visited.add(destination)
                        }
                        currentCnt = navigate(destination, currentCnt, visited)
                        visited.remove(destination)
                    }
                }
            }

            return currentCnt
        }

        val cnt = navigate("start", 0, mutableListOf())

        println(cnt)

    }

    fun part2(input: List<String>) {
        val paths = generatePaths(input)

        fun navigate(source: String, pathCnt: Int, visited: MutableList<String>, twice: String = "") : Int {
            var currentCnt = pathCnt
            var currentTwice = twice

            paths[source]!!.forEach { destination ->

                if (destination in visited) {
                    if (currentTwice.isEmpty()) {
                        currentTwice = destination
                    } else {
                        return@forEach
                    }
                }


                when (destination) {
                    "end" -> {
                        currentCnt++
                    }
                    else -> {
                        if (destination.isSmallCave()) {
                            visited.add(destination)
                        }

                        currentCnt = navigate(destination, currentCnt, visited, currentTwice)
                        if (currentTwice == destination) {
                            currentTwice = ""
                        }

                        visited.remove(destination)
                    }
                }
            }

            return currentCnt
        }

        val cnt = navigate("start", 0, mutableListOf())

        println(cnt)
    }

    val input = readInput("2021\\Day12")
    part1(input)
    part2(input)
}

