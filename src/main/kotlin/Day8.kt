class Day8 {
    fun task1(input: List<String>): Long {
        val instruction = input[0].toCharArray()
        val nodes = parseNodes(input)
        return walkMap(nodes, instruction, nodes.first { it.name == "AAA" }, "ZZZ")
    }

    fun task2(input: List<String>): Long {
        val instruction = input[0].toCharArray()
        val nodes = parseNodes(input)
        val paths = nodes.filter { it.name.endsWith("A") }.map { walkMap(nodes, instruction, it, "Z") }
        var result = paths[0]
        for (i in 1 until paths.size) {
            result = findLCM(result, paths[i])
        }
        return result
    }

    private fun walkMap(nodes: List<Node>, instruction: CharArray, startNode: Node, endNodeSuffix: String): Long {
        var steps = 0L
        var currentNode = startNode
        var iterator = instruction.iterator()
        while (!currentNode.name.endsWith(endNodeSuffix)) {
            if (iterator.hasNext()) {
                val direction = iterator.next()
                currentNode = when (direction) {
                    'L' -> nodes.first { it.name == currentNode.connections.first }
                    else -> nodes.first { it.name == currentNode.connections.second }
                }
                steps++
            } else {
                iterator = instruction.iterator()
            }
        }
        return steps
    }

    private fun parseNodes(input: List<String>) = input.drop(2).map {
        Node(it.take(3), Pair(it.substring(7, 10), it.substring(12, 15)))
    }

    private fun findLCM(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if (lcm % a == 0L && lcm % b == 0L) {
                return lcm
            }
            lcm += larger
        }
        return maxLcm
    }

    data class Node(val name: String, val connections: Pair<String, String>)
}