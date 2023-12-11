import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day11Test {

    @Test
    fun task1() {
        val testdata = File("src/test/resources/day11/input").readLines()
        assertEquals(374, Day11().task(testdata, 2))
    }

    @Test
    fun task2() {
        val testdata = File("src/test/resources/day11/input").readLines()
        assertEquals(1030, Day11().task(testdata, 10))
    }
}