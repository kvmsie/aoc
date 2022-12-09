import java.io.File

private const val PATH = "src/main/resources"

fun readInput(name: String) = File(PATH, "$name.txt")
    .readLines()

fun readInputAsString(name: String) = File(PATH, "$name.txt")
    .readText()

fun readInputAsInt(name: String) = File(PATH, "$name.txt")
    .readLines()
    .map {
        it.toInt()
    }

fun readInts(name: String) = File(PATH, "$name.txt")
    .readLines()
    .first()
    .split(",")
    .map {
        it.toInt()
    }

fun readInputAs2dInt(name: String) = File(PATH, "$name.txt")
    .readLines()
    .map { line ->
        line.map { char ->
            char.digitToInt()
        }.toTypedArray()
    }.toTypedArray()