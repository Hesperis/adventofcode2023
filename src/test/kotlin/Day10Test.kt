import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day10Test {

    @Test
    fun task1_simpleLoop() {
        val testdata = File("src/test/resources/day10/input1").readLines()
        assertEquals(4, Day10().task1(testdata))
    }

    @Test
    fun task1_complexLoop() {
        val testdata = File("src/test/resources/day10/input2").readLines()
        assertEquals(8, Day10().task1(testdata))
    }

    @Test
    fun task2_first() {
        val testdata = File("src/test/resources/day10/input3").readLines()
        assertEquals(8, Day10().task2(testdata))
    }

    @Test
    fun task2_second() {
        val testdata = File("src/test/resources/day10/input4").readLines()
        assertEquals(10, Day10().task2(testdata))
    }
}