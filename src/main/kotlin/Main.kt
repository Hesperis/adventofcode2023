import java.io.File

fun main(args: Array<String>) {
    val input = File("src/main/resources/day2/input").readLines()
    println(Day2().task1(input))
    println(Day2().task2(input))
}