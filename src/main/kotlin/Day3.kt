class Day3 {
    fun task1(input: List<String>): Int {
        val numbers = findAllNumbers(input)
        val symbols = findAllSymbols(input)
        return numbers.filter {
            it.hasAdjacentSymbol(symbols)
        }.sumOf { it.value }
    }

    fun task2(input: List<String>): Int {
        val numbers = findAllNumbers(input)
        val gears = findAllGears(input, numbers)
        return gears.sumOf { it.gearPair.first.value * it.gearPair.second.value }
    }
}

fun findAllNumbers(input: List<String>): List<Number> {
    val numbers = mutableListOf<Number>()
    input.mapIndexed { row, s ->
        var currentNumber = ""
        s.mapIndexed { index, c ->
            currentNumber = when {
                c.isDigit() -> currentNumber.plus(c)
                else -> {
                    if (currentNumber != "") numbers.add(Number(row, index - currentNumber.length, index, currentNumber.toInt()))
                    ""
                }
            }
        }
        if (currentNumber != "") numbers.add(Number(row, s.length - 1 - currentNumber.length, s.length-1, currentNumber.toInt()))
    }
    return numbers
}

fun findAllSymbols(input: List<String>): List<Symbol> {
    return input.mapIndexed { row, s ->
        s.mapIndexed { index, c ->
            if (c != '.' && !c.isDigit()) Symbol(row, index) else null
        }.filterNotNull()
    }.flatten()
}

fun findAllGears(input: List<String>, numbers: List<Number>): List<Gear> {
    return input.mapIndexed { row, s ->
        s.mapIndexed { index, c ->
            if (c != '.' && !c.isDigit() && c == '*') {
                val attachedNumbers = numbers.filter {
                    it.row in row - 1..row + 1 &&
                            index in it.startIndex - 1..it.endIndex
                }
                if (attachedNumbers.size == 2) Gear(Symbol(row, index), Pair(attachedNumbers[0], attachedNumbers[1])) else null
            } else null
        }.filterNotNull()
    }.flatten()
}
fun Number.hasAdjacentSymbol(symbols: List<Symbol>): Boolean {
    return symbols.any {
        it.row in this.row - 1..this.row + 1 &&
                it.index in this.startIndex - 1..this.endIndex
    }
}


data class Number (val row: Int, val startIndex: Int, val endIndex: Int, val value: Int)
data class Symbol (val row: Int, val index: Int)

data class Gear (val symbol: Symbol, val gearPair: Pair<Number, Number>)