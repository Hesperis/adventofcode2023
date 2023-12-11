import java.io.File

fun main(args: Array<String>) {
    val input = File("src/main/resources/day11/input").readLines()
    println(Day11().task(input, 2))
    println(Day11().task(input, 1000000))
}