fun main() {
    fun part1(input: List<String>) {
        var gamma = ""
        var epsilon = ""

        for (i in 0 until input.first().length) {
            val report = input.map { it[i] }
                .groupingBy { it }
                .eachCount()

            val newGamma = report.maxByOrNull { it.value }?.key
            val newEpsilon = report.minByOrNull { it.value }?.key

            gamma += newGamma
            epsilon += newEpsilon
        }

        val result = gamma.toInt(2) * epsilon.toInt(2)
        println(result)
    }

    fun part2(input: List<String>) {
        var scrubberRating = input
        var oxygenRating = input

        for (i in 0 until input.first().length) {
            val scrubberBit = scrubberRating.map { it[i] }
                .groupingBy { it }
                .eachCount()
                .toList()
                .sortedWith(
                    compareBy( { it.second }, { it.first } )
                ).first()
                .first

            val oxygenBit = oxygenRating.map { it[i] }
                .groupingBy { it }
                .eachCount()
                .toList()
                .sortedWith(
                    compareBy( { it.second }, { it.first } )
                ).last()
                .first

            scrubberRating = scrubberRating.filter {
                it[i] == scrubberBit
            }

            oxygenRating = oxygenRating.filter {
                it[i] == oxygenBit
            }
        }

        val scrubber = scrubberRating.first().toInt(2)
        val oxygen = oxygenRating.first().toInt(2)

        println(scrubber * oxygen)
    }

    val input = readInput("Day03")
    part1(input)
    part2(input)
}