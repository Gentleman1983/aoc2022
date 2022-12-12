package de.havox_design.aoc2022.day12

class HillClimbingAlgorithm(private var filename: String) {
    var landscape = readFile()

    private fun readFile(): Landscape {
        val fileInput = getResourceAsText(filename)

        return Landscape(fileInput[0].length, fileInput.size, fileInput)
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
