import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day3Test {

    @Test
    fun task1() {
        val testdata = File("src/test/resources/day3/input").readLines()
        assertEquals(4361, Day3().task1(testdata))
    }

    @Test
    fun task2() {
        val testdata = File("src/test/resources/day3/input").readLines()
        assertEquals(467835, Day3().task2(testdata))
    }

    @Test
    fun `has adjacent symbol should return true if there is an adjacent symbol`() {
        val numberAbove = Number(1, 20, 23, 888)
        val numberLeft = Number(2, 20, 22, 88)
        val numberRight = Number(2, 23, 25, 888)
        val numberBelow = Number(3, 20, 23, 888)
        val diagonalLeftBelow = Number(3, 20, 22, 888)
        val diagonalRightAbove = Number(1, 23, 25, 888)
        val notInRange = Number(3, 24, 26, 888)
        val symbols = listOf<Symbol>(
            Symbol(2, 22)
        )
        assert(numberAbove.hasAdjacentSymbol(symbols))
        assert(numberLeft.hasAdjacentSymbol(symbols))
        assert(numberRight.hasAdjacentSymbol(symbols))
        assert(numberBelow.hasAdjacentSymbol(symbols))
        assert(diagonalLeftBelow.hasAdjacentSymbol(symbols))
        assert(diagonalRightAbove.hasAdjacentSymbol(symbols))
        assert(!notInRange.hasAdjacentSymbol(symbols))
    }
}