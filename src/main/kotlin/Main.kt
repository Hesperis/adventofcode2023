import java.io.File

fun main(args: Array<String>) {
    val input = File("src/main/resources/day3/input").readLines()
    println(Day3().task1(input))
    println(Day3().task2(input))
}