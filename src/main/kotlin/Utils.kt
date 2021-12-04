import java.io.File

fun readInput(name: String) = File("src\\main\\resources", "$name.txt").readLines()

fun readInputAsInt(name: String) = File("src\\main\\resources", "$name.txt").readLines().map { it.toInt() }
