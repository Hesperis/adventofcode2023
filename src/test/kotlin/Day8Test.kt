import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day8Test {

    @Test
    fun task1_simpleCase() {
        val testdata = File("src/test/resources/day8/input1").readLines()
        assertEquals(2, Day8().task1(testdata))
    }

    @Test
    fun task1_complexCase() {
        val testdata = File("src/test/resources/day8/input2").readLines()
        assertEquals(6, Day8().task1(testdata))
    }

    @Test
    fun task2() {
        val testdata = File("src/test/resources/day8/input3").readLines()
        assertEquals(6, Day8().task2(testdata))
    }
}