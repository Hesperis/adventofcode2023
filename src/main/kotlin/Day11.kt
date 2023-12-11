import kotlin.math.abs

class Day11 {
    fun task(input: List<String>, multiplier: Int): Long {
        val smallUniverse = createUniverse(input)
        val galaxies = expandUniverse(smallUniverse, multiplier).pairOff()

        return galaxies.sumOf { galaxyPair ->
            abs(galaxyPair.second.second - galaxyPair.first.second) + abs(galaxyPair.second.first - galaxyPair.first.first)
        }
    }

    private fun createUniverse(input: List<String>): List<Pair<Long, Long>> {
        return input.mapIndexed{ row: Int, string ->
            string.mapIndexed { column: Int, c: Char ->
                if (c == '#') Pair(row.toLong(), column.toLong()) else null
            }.filterNotNull()
        }.flatten()
    }
    private fun expandUniverse(universe: List<Pair<Long, Long>>, multiplier: Int): List<Pair<Long, Long>> {
        val columnsToExpand = (0 .. universe.maxOf { it.second }).filter { i -> universe.none { i == it.second } }
        val rowsToExpand = (0 .. universe.maxOf { it.first }).filter { i -> universe.none { i == it.first } }

        return universe.map { galaxy ->
            val newRow = rowsToExpand.filter { row -> row < galaxy.first }.size.times(multiplier - 1) + galaxy.first
            val newColumn = columnsToExpand.filter { column -> column < galaxy.second }.size.times(multiplier - 1) + galaxy.second
            Pair(newRow, newColumn)
        }
    }

    private fun <T> List<T>.pairOff(): List<Pair<T, T>> {
        return this.mapIndexed { index: Int, t: T ->
            this.subList(index, this.size).map { Pair(t, it) }
        }.flatten().filterNot { it.first == it.second }
    }
}