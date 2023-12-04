import java.io.File

fun main(args: Array<String>) {
    val input = File("src/main/resources/day4/input").readLines()
    println(Day4().task1(input))
    println(Day4().task2(input))
}