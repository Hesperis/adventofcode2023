class Day7 {
    fun task1(input: List<String>): Int {
        val hands = buildHands(input)
        val sortedHands =
            hands.sortedDescending().groupBy { it.type() }.flatMap { hand -> hand.value.sortedBy { card -> card.cardStrength() } }
        return sortedHands.foldIndexed(0) { index, acc, hand -> acc + (index + 1) * hand.bid }
    }

    fun task2(input: List<String>): Int {
        val hands = buildHands(input, true)
        val sortedHands =
            hands.sortedDescending().groupBy { it.type() }.flatMap { hand -> hand.value.sortedBy { card -> card.cardStrength(true) } }
        return sortedHands.foldIndexed(0) { index, acc, hand -> acc + (index + 1) * hand.bid }
    }

    private fun buildHands(input: List<String>, jokerEnabled: Boolean = false): List<Hand> {
        return input.map {
            val split = it.split("\\s+".toRegex())
            val cards = split[0].map { char -> Card(char) }
            Hand(cards, split[1].toInt(), jokerEnabled)
        }
    }

    data class Hand(val cards: List<Card>, val bid: Int, val jokerEnabled: Boolean = false): Comparable<Hand> {

        fun type(): Type {
            return if (jokerEnabled) typeWithJoker() else typeWithoutJoker()
        }
        private fun typeWithoutJoker(): Type {
            val matchingCards = this.cards.groupBy { it.label }.values.toList()
            return when(matchingCards.size) {
                1 -> Type.FIVE_OF_A_KIND
                2 -> if (matchingCards.any { it.size == 4 }) Type.FOUR_OF_A_KIND else Type.FULL_HOUSE
                3 -> if (matchingCards.any { it.size == 3 }) Type.THREE_OF_A_KIND else Type.TWO_PAIR
                4 -> Type.ONE_PAIR
                else -> Type.HIGH_CARD
            }
        }

        private fun typeWithJoker(): Type {
            val matchingCardGroups = this.cards.groupBy { it.label }
            val matchingCards = matchingCardGroups.values.toList()
            val hasJokers = matchingCardGroups.keys.contains('J')
            return when(matchingCards.size) {
                1 -> Type.FIVE_OF_A_KIND
                2 -> when {
                    hasJokers -> Type.FIVE_OF_A_KIND
                    matchingCards.any { it.size == 4 } -> Type.FOUR_OF_A_KIND
                    else -> Type.FULL_HOUSE
                }
                3 -> when {
                    hasJokers && matchingCardGroups['J']!!.size >= 2 -> Type.FOUR_OF_A_KIND
                    hasJokers && matchingCardGroups['J']!!.size == 1 -> when {
                        matchingCards.any { it.size == 3 } -> Type.FOUR_OF_A_KIND
                        else -> Type.FULL_HOUSE
                    }
                    matchingCards.any { it.size == 3 } -> Type.THREE_OF_A_KIND
                    else -> Type.TWO_PAIR
                }
                4 -> when {
                    hasJokers -> Type.THREE_OF_A_KIND
                    else -> Type.ONE_PAIR
                }
                else -> when {
                    hasJokers -> Type.ONE_PAIR
                    else -> Type.HIGH_CARD
                }
            }
        }

        fun cardStrength(jokerEnabled: Boolean = false): Int {
            return this.cards.joinToString("") { it.strength(jokerEnabled) }.toInt()
        }

        override fun toString(): String {
            return this.cards.map { it.label }.joinToString("")
        }

        override fun compareTo(other: Hand): Int {
            return if(jokerEnabled) compareValuesBy(this, other) { it.typeWithJoker() } else compareValuesBy(this, other) { it.type() }
        }
    }

    enum class Type(val strength: Int) {
        FIVE_OF_A_KIND(7),
        FOUR_OF_A_KIND(6),
        FULL_HOUSE(5),
        THREE_OF_A_KIND(4),
        TWO_PAIR(3),
        ONE_PAIR(2),
        HIGH_CARD(1)
    }
    data class Card(val label: Char) {
        fun strength(jokerEnabled: Boolean = false): String {
            return when(this.label) {
                'A' -> "14"
                'K' -> "13"
                'Q' -> "12"
                'J' -> if(jokerEnabled) "01" else "11"
                'T' -> "10"
                else -> this.label.toString().padStart(2, '0')
            }
        }
    }
}