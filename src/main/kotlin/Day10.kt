import Day10.Direction.*

class Day10 {
    fun task1(input: List<String>): Int {
        val row = input.indexOfFirst { s -> s.contains('S') }
        val column = input.first { s -> s.contains('S') }.indexOf('S')

        val firstMove = findFirstMove(row, column, input)
        return input.walk(firstMove.first, firstMove.second, firstMove.third, mutableMapOf()).size / 2
    }

    fun task2(input: List<String>): Int {
        val row = input.indexOfFirst { s -> s.contains('S') }
        val column = input.first { s -> s.contains('S') }.indexOf('S')

        val firstMove = findFirstMove(row, column, input)
        val loop = input.walk(firstMove.first, firstMove.second, firstMove.third, mutableMapOf())

        return findNumberOfPoints(input, loop, firstMove)
    }

    private fun findNumberOfPoints(
        input: List<String>,
        loop: Map<Pair<Int, Int>, Direction>,
        firstMove: Triple<Int, Int, Direction>
    ) = input.mapIndexed { row, s ->
        s.mapIndexed { column, _ ->
            val coordinates = Pair(row, column)
            if (coordinates !in loop.keys) {
                var crossings = 0
                var foldDirection = RIGHT
                for (i in row - 1 downTo 0) {
                    val currentPoint = Pair(i, column)
                    if (currentPoint in loop.keys) {
                        when (input[currentPoint.first][currentPoint.second]) {
                            '-' -> crossings++
                            'J' -> foldDirection = LEFT
                            '7' -> if (foldDirection == RIGHT) crossings++
                            'L' -> foldDirection = RIGHT
                            'F' -> if (foldDirection == LEFT) crossings++
                            'S' -> if (firstMove.third != foldDirection) crossings++ else foldDirection =
                                firstMove.third
                        }
                    }
                }
                return@mapIndexed if (crossings % 2 == 0) 0 else 1
            }
            return@mapIndexed 0
        }.sum()
    }.sum()

    private fun findFirstMove(row: Int, column: Int, map: List<String>): Triple<Int, Int, Direction> {
        val right = if (column < map[0].length) map[row][column + 1] else null
        val down = if (row < map.size) map[row + 1][column] else null
        val up = if (row > 0) map[row - 1][column] else null
        val left = if (column > 0) map[row][column - 1] else null

        return if (right in listOf('-', 'J', '7')) Triple(row, column + 1, RIGHT)
        else if (down in listOf('|', 'L', 'J')) Triple (row + 1, column, DOWN)
        else if (left in listOf('-', 'L', 'F')) Triple (row, column - 1, LEFT)
        else if (up in listOf('F', '7', '|')) Triple (row - 1, column, RIGHT)
        else throw Exception()
    }

    private tailrec fun List<String>.walk(row: Int, column: Int, direction: Direction, loop: MutableMap<Pair<Int, Int>, Direction>): Map<Pair<Int, Int>, Direction> {
        loop[row to column] = direction
        return when (this[row][column]) {
            '-' -> when(direction) {
                RIGHT -> this.walk(row, column+1, RIGHT, loop)
                else -> this.walk(row, column - 1, LEFT, loop)
            }
            '|' -> when(direction) {
                DOWN -> this.walk(row + 1, column, DOWN, loop)
                else -> this.walk(row - 1, column, UP, loop)
            }
            'L' -> when(direction) {
                DOWN -> this.walk(row, column + 1, RIGHT, loop)
                else -> this.walk(row - 1, column, UP, loop)
            }
            'F' -> when(direction) {
                LEFT -> this.walk(row + 1, column, DOWN, loop)
                else -> this.walk(row, column + 1, RIGHT, loop)
            }
            'J' -> when(direction) {
                RIGHT -> this.walk(row - 1, column, UP, loop)
                else -> this.walk(row, column - 1, LEFT, loop)
            }
            '7' -> when(direction) {
                UP -> this.walk(row, column - 1, LEFT, loop)
                else -> this.walk(row + 1, column, DOWN, loop)
            }
            else -> {
                println("stopping at ${this[row][column]} with ${loop.size} steps")
                return loop
            }
        }
    }
    enum class Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}