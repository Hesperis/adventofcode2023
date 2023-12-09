import java.io.File

fun main(args: Array<String>) {
    val input = File("src/main/resources/day8/input").readLines()
    println(Day8().task1(input))
    println(Day8().task2(input))
}