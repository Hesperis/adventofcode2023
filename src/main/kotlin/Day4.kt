class Day4 {
    fun task1(input: List<String>): Int {
        return input.sumOf { scratchcard ->
            val (winningNumbers, actualNumbers) = parseScratchCard(scratchcard)
            var score = 0
            actualNumbers.forEach {
                if (it in winningNumbers) {
                    when (score) {
                        0 -> score = 1
                        else -> score *= 2
                    }
                }
            }
            score
        }
    }

    fun task2(input: List<String>): Int {
        val maxCardNumber = input.size
        val cards = input.map { scratchcard ->
            val (winningNumbers, actualNumbers) = parseScratchCard(scratchcard)
            Card(winningNumbers, actualNumbers, 1)
        }
        cards.forEachIndexed { index, card ->
            var hits = card.actualNumbers.filter { it in card.winningNumbers }.size
            if (index + hits > maxCardNumber) hits = maxCardNumber - index
            repeat(card.amount) {
                for (i in 1 .. hits) {
                    cards[index + i].amount++
                }
            }
        }
        return cards.sumOf { card -> card.amount }
    }

    private fun parseScratchCard(scratchcard: String): Pair<List<String>, List<String>> {
        val splitList = scratchcard.substringAfter(": ").split("| ")
        val winningNumbers =
            splitList[0].trim().split(" ").map { it.takeWhile { it.isDigit() }.toString() }.filterNot { it == "" }
        val actualNumbers =
            splitList[1].trim().split(" ").map { it.takeWhile { it.isDigit() }.toString() }.filterNot { it == "" }
        return Pair(winningNumbers, actualNumbers)
    }

    data class Card (val winningNumbers: List<String>, val actualNumbers: List<String>, var amount: Int)
}