class Day2 {
    fun task1(input: List<String>): Int {
        val maxRed = 12
        val maxGreen = 13
        val maxBlue = 14
        var sum = 0
        input.mapIndexed { index, game ->
            game.substringAfter(':').trim().split(";").map { draw ->
                draw.trim().split(',').map { cubes ->
                    val number = cubes.trim().split(' ')[0].toInt()
                    val color = cubes.trim().split(' ')[1].trim()
                    when (color) {
                        "blue" -> if (number > maxBlue) return@mapIndexed
                        "red" -> if (number > maxRed) return@mapIndexed
                        "green" -> if (number > maxGreen) return@mapIndexed
                    }
                }
            }
            sum += index + 1
        }
        return sum
    }

    fun task2(input: List<String>): Int {
        return input.sumOf {game ->
            var maxRed = 0
            var maxGreen = 0
            var maxBlue = 0
            game.substringAfter(':').trim().split(";").map { draw ->
                draw.trim().split(',').map { cubes ->
                    val number = cubes.trim().split(' ')[0].toInt()
                    val color = cubes.trim().split(' ')[1].trim()
                    when (color) {
                        "blue" -> if (number > maxBlue) maxBlue = number
                        "red" -> if (number > maxRed) maxRed = number
                        "green" -> if (number > maxGreen) maxGreen = number
                    }
                }
            }
            maxBlue*maxRed*maxGreen
        }
    }
}
