class Day9 {
    fun task1(input: List<String>): Long {
        val sequences = input.map { it.parseInput() }.toMutableList()
        return sequences.sumOf {
            val sequenceTree = buildTree(it)
            sequenceTree.reversed().fold(0) { acc, ints -> acc + ints.last() }.toLong()
        }
    }

    fun task2(input: List<String>): Long {
        val sequences = input.map { it.parseInput() }.toMutableList()
        return sequences.sumOf {
            val result = buildTree(it).reversed().windowed(2, 1).map { pair ->
                val extrapolatedValue = pair[1][0] - pair[0][0]
                pair[1].add(0, extrapolatedValue)
                extrapolatedValue
            }.last()
            result.toLong()
        }
    }

    private fun buildTree(it: MutableList<Int>): MutableList<MutableList<Int>> {
        val sequenceTree = mutableListOf<MutableList<Int>>(it)
        var currentList = it
        while (!currentList.all { value -> value == 0 }) {
            currentList = currentList.parseSequence()
            sequenceTree.add(currentList)
        }
        return sequenceTree
    }

    private fun MutableList<Int>.parseSequence() = this.windowed(2,1).map { it[1] - it[0] }.toMutableList()

    private fun String.parseInput() = this.split("\\s+".toRegex()).map { it.toInt() }.toMutableList()
}