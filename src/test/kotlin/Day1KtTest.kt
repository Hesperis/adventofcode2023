import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

class Day1Test {

    val day = Day1()

    @Test
    fun day1task1() {
        val testdata = File("src/test/resources/day1/input").readLines()
        assertEquals(142, day.task1(testdata))
    }

    @Test
    fun day1task2() {
        val testdata = File("src/test/resources/day1/input2").readLines()
        assertEquals(281, day.task2(testdata))
    }
}