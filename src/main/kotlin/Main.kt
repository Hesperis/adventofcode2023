import java.io.File

fun main(args: Array<String>) {
    val input = File("src/main/resources/day1/input").readLines()
    println(Day1().day1task1(input))
    println(Day1().day1task2(input))
}