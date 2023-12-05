import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day5Test {

    @Test
    fun task1() {
        val testdata = File("src/test/resources/day5/input").readLines()
        assertEquals(35, Day5().task1(testdata))
    }

    @Test
    fun task2() {
        val testdata = File("src/test/resources/day5/input").readLines()
        assertEquals(46, Day5().task2(testdata))
    }
}