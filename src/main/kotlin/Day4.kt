import kotlin.math.min
import kotlin.math.pow

class Day4 {
    fun task1(input: List<String>): Int {
        return input.sumOf { scratchcard ->
            val winningNumbers = parseScratchCard(scratchcard)
            winningNumbers.indices.fold(0.0) { _, i -> 2.toDouble().pow(i) } }.toInt()
    }

    fun task2(input: List<String>): Int {
        val cards = input.map { scratchcard ->
            val actualNumbers = parseScratchCard(scratchcard)
            Card(actualNumbers, 1)
        }
        cards.forEachIndexed { index, card ->
            val hits = min(card.winningNumbers.size, input.size - index)
            repeat(card.amount) {
                for (i in 1 .. hits) { cards[index + i].amount++ }
            }
        }
        return cards.sumOf { card -> card.amount }
    }

    private fun parseScratchCard(scratchcard: String): List<String> {
        val splitList = scratchcard.substringAfter(": ").split("| ")
        val potentialWinners = splitList[0].trim().split("\\s+".toRegex())
        return splitList[1].trim().split("\\s+".toRegex()).filter { number -> number in potentialWinners }
    }

    data class Card (val winningNumbers: List<String>, var amount: Int)
}