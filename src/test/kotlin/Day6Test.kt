import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day6Test {

    @Test
    fun task1() {
        val testdata = File("src/test/resources/day6/input").readLines()
        assertEquals(288, Day6().task1(testdata))
    }

    @Test
    fun task2() {
        val testdata = File("src/test/resources/day6/input").readLines()
        assertEquals(71503, Day6().task2(testdata))
    }
}