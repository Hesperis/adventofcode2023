import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day9Test {

    @Test
    fun task1() {
        val testdata = File("src/test/resources/day9/input").readLines()
        assertEquals(114, Day9().task1(testdata))
    }

    @Test
    fun task2() {
        val testdata = File("src/test/resources/day9/input").readLines()
        assertEquals(2, Day9().task2(testdata))
    }
}