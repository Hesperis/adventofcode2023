class Day1 {
    fun task1(input: List<String>): Int {
        return input.sumOf { line ->
            val digit1 = line.first { char -> char.isDigit() }
            val digit2 = line.last { char -> char.isDigit() }
            listOf<Char>(digit1,digit2).joinToString("").toInt()
        }
    }

    fun task2(input: List<String>): Int {
        val replacements = mapOf<String, String>("one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9")

        return input.sumOf {line ->
            val digit1 = line.findFirstDigitOrDigitString(replacements)
            val digit2 = line.findLastDigitOrDigitString(replacements)
            listOf<Char>(digit1,digit2).joinToString("").toInt()
        }
    }

    private fun String.findFirstDigitOrDigitString(replacements: Map<String, String>): Char {
        val firstStringValue = this.findAnyOf(replacements.keys)?.second
        val newString = if (firstStringValue != null) this.replace(firstStringValue, replacements[firstStringValue]!!) else this
        return newString.first { it.isDigit() }
    }

    private fun String.findLastDigitOrDigitString(replacements: Map<String, String>): Char {
        val lastStringValue = this.findLastAnyOf(replacements.keys)?.second
        val newString = if (lastStringValue != null) this.replace(lastStringValue, replacements[lastStringValue]!!) else this
        return newString.last { it.isDigit() }
    }
}

