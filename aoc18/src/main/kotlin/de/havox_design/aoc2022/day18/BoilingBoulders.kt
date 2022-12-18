package de.havox_design.aoc2022.day18

class BoilingBoulders(private var filename: String) {
    val data = readFile()

    fun processPart1(): Int =
        data
            .sumOf { point -> point.neighbours()
                .count { it !in data } }

    fun processPart2(): Int =
        0

    private fun readFile(): Set<Point3D> {
        val fileData = getResourceAsText(filename)

        return fileData
            .map { it.split(",")
                .map(String::toInt) }
            .map { (x, y, z) -> Point3D(x, y, z) }
            .toSet()
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
