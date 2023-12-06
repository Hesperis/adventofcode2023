import java.io.File

fun main(args: Array<String>) {
    val input = File("src/main/resources/day6/input").readLines()
    println(Day6().task1(input))
    println(Day6().task2(input))
}