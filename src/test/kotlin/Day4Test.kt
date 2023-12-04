import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day4Test {
    @Test
    fun task1() {
        val testdata = File("src/test/resources/day4/input").readLines()
        assertEquals(13, Day4().task1(testdata))
    }

    @Test
    fun task2() {
        val testdata = File("src/test/resources/day4/input").readLines()
        assertEquals(30, Day4().task2(testdata))
    }
}