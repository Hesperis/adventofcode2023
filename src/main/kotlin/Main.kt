import java.io.File

fun main(args: Array<String>) {
    val input = File("src/main/resources/day7/input").readLines()
    println(Day7().task1(input))
    println(Day7().task2(input))
}