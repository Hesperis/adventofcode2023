import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day2Test {

    @Test
    fun day2task1() {
        val testdata = File("src/test/resources/day2/input").readLines()
        assertEquals(8, Day2().task1(testdata))
    }

    @Test
    fun day2task2() {
        val testdata = File("src/test/resources/day2/input").readLines()
        assertEquals(2286, Day2().task2(testdata))
    }
}