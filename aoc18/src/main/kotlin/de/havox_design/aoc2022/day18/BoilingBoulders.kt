package de.havox_design.aoc2022.day18

class BoilingBoulders(private var filename: String) {
    private val data = readFile()

    fun processPart1(): Int =
        data
            .sumOf { point ->
                point
                    .neighbours()
                    .count { it !in data }
            }

    fun processPart2(): Int {
        val xLimits = data.minOf { it.x } - 1..data.maxOf { it.x } + 1
        val yLimits = data.minOf { it.y } - 1..data.maxOf { it.y } + 1
        val zLimits = data.minOf { it.z } - 1..data.maxOf { it.z } + 1

        val toVisit = mutableListOf(Point3D(xLimits.first, yLimits.first, zLimits.first))
        val visited = mutableSetOf<Point3D>()

        var sides = 0
        while (toVisit.isNotEmpty()) {
            val current = toVisit.removeFirst()
            if (current !in visited) {
                current.neighbours()
                    .filter { it.x in xLimits && it.y in yLimits && it.z in zLimits }
                    .forEach { next -> if (next in data) sides++ else toVisit += next }
                visited += current
            }
        }
        return sides
    }

    private fun readFile(): Set<Point3D> {
        val fileData = getResourceAsText(filename)

        return fileData
            .map { row ->
                row
                    .split(",")
                    .map(String::toInt)
            }
            .map { (x, y, z) -> Point3D(x, y, z) }
            .toSet()
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
