import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day7Test {

    @Test
    fun task1() {
        val testdata = File("src/test/resources/day7/input").readLines()
        assertEquals(6440, Day7().task1(testdata))
    }

    @Test
    fun task2() {
        val testdata = File("src/test/resources/day7/input").readLines()
        assertEquals(5905, Day7().task2(testdata))
    }

    @Test
    fun testTypeWithJoker() {
        val inputs = listOf<Pair<String, Day7.Type>>(
            "AAAAA" to Day7.Type.FIVE_OF_A_KIND,
            "JAAAA" to Day7.Type.FIVE_OF_A_KIND,
            "KAAAA" to Day7.Type.FOUR_OF_A_KIND,
            "KJAAA" to Day7.Type.FOUR_OF_A_KIND,
            "KJJAA" to Day7.Type.FOUR_OF_A_KIND,
            "KJJJA" to Day7.Type.FOUR_OF_A_KIND,
            "KKAAA" to Day7.Type.FULL_HOUSE,
            "KKJAA" to Day7.Type.FULL_HOUSE,
            "KTAAA" to Day7.Type.THREE_OF_A_KIND,
            "KTJAA" to Day7.Type.THREE_OF_A_KIND,
            "KTJJA" to Day7.Type.THREE_OF_A_KIND,
            "KTTAA" to Day7.Type.TWO_PAIR,
            "KT45J" to Day7.Type.ONE_PAIR,
            "KT455" to Day7.Type.ONE_PAIR,
            "23456" to Day7.Type.HIGH_CARD
        )
        inputs.map { assertEquals(it.second, generateJokerHand(it.first).type()) }
    }

    private fun generateJokerHand(string: String): Day7.Hand {
        return Day7.Hand(string.map { Day7.Card(it) }, 0, true)
    }
}