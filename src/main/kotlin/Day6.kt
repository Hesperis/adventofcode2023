import kotlin.math.*

class Day6 {
    fun task1(input: List<String>): Int {
        val races = input[0].cleanAndSplit().zip(input[1].cleanAndSplit())
        val equation = races.map { solveTheEquation(it.first, it.second) }
        return equation.map { it.size }.reduce { acc, i -> acc * i }
    }

    fun task2(input: List<String>): Int {
        val raceMaxTime = input[0].filter { it.isDigit() }.toLong()
        val record = input[1].filter { it.isDigit() }.toLong()
        return solveTheEquation(raceMaxTime, record).size
    }

    private fun solveTheEquation(raceMaxTime: Long, record: Long): List<Long> {
        /**
         * Finds the roots the quadratic problem where we treat the races as an equation looking like this:
         * record < x * (raceMaxTime - x).
         * We solve it first for the case where record = x * (raceMaxTime - x), and then return all Longs found between
         * those two points. We assume that there will always be two real, positive roots, and that ties are not allowed
         * (hence the slightly awkward construction of our LongRange in the return function).
         *
         * The equation can be rewritten as -x^2 + raceMaxTime*x - record = 0, meaning our values for a, b and c in the
         * shape ax^2 + bx + c = 0 are -1, raceMaxTime and -record respectively. This gives the determinant function
         * below, where the shape is b^2 - 4ac.
         */
        val determinant: Double = (raceMaxTime * raceMaxTime - 4 * -1 * -record).toDouble()
        val root1 = (-raceMaxTime + sqrt(determinant)) / (2 * -1)
        val root2 = (-raceMaxTime - sqrt(determinant)) / (2 * -1)
        return (floor(root1).toLong() + 1 until ceil(root2).toLong()).toList()
    }

    private fun String.cleanAndSplit(): List<Long> = this.substringAfter(':').split("\\s+".toRegex()).filterNot { it == "" }.map { it.toLong() }
}