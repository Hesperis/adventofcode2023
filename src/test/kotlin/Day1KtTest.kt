import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

class Day1KtTest {

    val day = Day1()

    @Test
    fun day1task1() {
        val testdata = File("src/test/resources/day1/input").readLines()
        assertEquals(142, day.day1task1(testdata))
    }

    @Test
    fun day1task2() {
        val testdata = File("src/test/resources/day1/input2").readLines()
        assertEquals(281, day.day1task2(testdata))
    }
}