class Day6 {
    fun task1(input: List<String>): Int {
        val races = input[0].cleanAndSplit().zip(input[1].cleanAndSplit())
        val allResults = races.map { getAllRecordResults(it.first, it.second) }
        return allResults.map { it.size }.reduce { acc, i -> acc * i }
    }

    fun task2(input: List<String>): Int {
        val raceMaxTime = input[0].filter { it.isDigit() }.toLong()
        val record = input[1].filter { it.isDigit() }.toLong()
        return getAllRecordResults(raceMaxTime, record).size
    }

    private fun getAllRecordResults(raceMaxTime: Long, record: Long): List<Long> {
        return List(raceMaxTime.toInt()) { it * (raceMaxTime - it) }.filter { it > record }
    }

    private fun String.cleanAndSplit(): List<Long> = this.substringAfter(':').split("\\s+".toRegex()).filterNot { it == "" }.map { it.toLong() }
}