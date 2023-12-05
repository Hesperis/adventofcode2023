import kotlinx.coroutines.*

class Day5 {
    fun task1(input: List<String>): Long {
        val seeds = parseSeeds(input)
        val transformers = input.drop(2).filter { it == "" || it.first().isDigit() }.parseTransformers()
        return seeds.minOf { seed -> traverseTransformers(seed, transformers) }
    }

    fun task2(input: List<String>): Long {
        val seedPairs: List<Pair<Long, Long>> = parseSeeds(input)
            .chunked(2).map {
                Pair(it[0], it[1])
            }
        val transformers = input.drop(2).filter { it == "" || it.first().isDigit() }.parseTransformers()
        return runBlocking {
            coroutineScope {
                seedPairs.map {
                    async(Dispatchers.Default) {
                        val seeds = (it.first until it.first + it.second).asSequence()
                        seeds.minOf { seed -> traverseTransformers(seed, transformers) }
                    }
                }.awaitAll()
            }.min()
        }
    }

    private fun parseSeeds(input: List<String>) =
        input[0].substringAfter(":").trim().split("\\s+".toRegex()).map { it.toLong() }

    private fun List<String>.parseTransformers() = sequence {
        val inputSequence = this@parseTransformers.asSequence()
        val buffer = mutableListOf<GardenTriple>()
        for (current in inputSequence) {
            val shouldSplit = current == ""
            if (shouldSplit) {
                yield(buffer.toList())
                buffer.clear()
            } else buffer.add(current.splitToTriple())
        }
        if (buffer.isNotEmpty()) yield(buffer)
    }.toList()

    private fun traverseTransformers(
        seed: Long,
        transformers: List<List<GardenTriple>>
    ) = transformers.fold(seed) { acc, gardenTriples ->
            val applicableMapping = gardenTriples.find { acc in it.sourceRange..it.sourceRange + it.length }
            if (applicableMapping != null) applicableMapping.destinationRange + (acc - applicableMapping.sourceRange) else acc
        }


    private fun String.splitToTriple(): GardenTriple {
        val list = this.split("\\s+".toRegex())
        return GardenTriple(list[0].toLong(), list[1].toLong(), list[2].toLong())
    }

    data class GardenTriple(
        val destinationRange: Long,
        val sourceRange: Long,
        val length: Long
    )
}