import java.io.File

fun main(args: Array<String>) {
    val input = File("src/main/resources/day9/input").readLines()
    println(Day9().task1(input))
    println(Day9().task2(input))
}