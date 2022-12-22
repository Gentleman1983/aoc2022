package de.havox_design.aoc2022.day22

class MonkeyMap(private var filename: String) {
    val data = readFile()
    val orders = readOrders()

    fun processPart1(): Int =
        0

    fun processPart2(): Int =
        0

    private fun readFile(): List<String> {
        val input = getResourceAsText(filename).toMutableList()
        val orderRow = input[input.size - 1]
        input -= orderRow

        return input
    }


    private fun readOrders(): List<String> {
        val input = getResourceAsText(filename)
        val orderRow = input[input.size - 1]

        return parseOrders(orderRow)
    }

    private fun parseOrders(orderRow: String): List<String> {
        return listOf<String>()
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
