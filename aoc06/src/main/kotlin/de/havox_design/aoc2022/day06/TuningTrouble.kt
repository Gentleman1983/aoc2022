package de.havox_design.aoc2022.day06

class TuningTrouble(private val filename: String) {
    private var data: String = ""

    fun processPart1(): Int {
        readData()

        for (index in 4..data.length) {
            val window = data.substring(index - 4, index)

            if (DetectionWindow(window).isPacketMarker()) {
                return index
            }
        }

        return -666
    }

    fun processPart2(): Int {
        readData()

        for (index in 14..data.length) {
            val window = data.substring(index - 14, index)

            if (DetectionWindow(window).isMessageMarker()) {
                return index
            }
        }

        return -666
    }

    private fun readData() {
        val fileData = getResourceAsText(filename)
        data = fileData!![0]
    }

    private fun getResourceAsText(path: String): List<String>? =
        this.javaClass.classLoader.getResourceAsStream(path)?.bufferedReader()?.readLines()
}
