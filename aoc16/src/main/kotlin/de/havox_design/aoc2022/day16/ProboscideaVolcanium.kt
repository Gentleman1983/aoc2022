package de.havox_design.aoc2022.day16

class ProboscideaVolcanium(private var filename: String) {
    val valves = readFile()


    fun processPart1(): Int =
        0

    fun processPart2(): Int =
        0

    private fun readFile(): Map<String, Valve> {
        val fileData = getResourceAsText(filename)

        return fileData
            .map(Valve::from)
            .associateBy(Valve::name)
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
