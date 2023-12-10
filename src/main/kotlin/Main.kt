import java.io.File

fun main(args: Array<String>) {
    val input = File("src/main/resources/day10/input").readLines()
    println(Day10().task1(input))
    println(Day10().task2(input))
}