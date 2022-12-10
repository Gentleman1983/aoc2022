package de.havox_design.aoc2022.day09

class RopeBridge(private var filename: String) {
    val moves = readFile()
    fun readFile(): List<Move> {
        val fileData = getResourceAsText(filename)
        val data = emptyList<Move>().toMutableList()

        for (row in fileData) {
            val elements = row.split(" ")
            val direction = Direction.findDirectionBySymbol(elements[0])
            val numberOfFields = elements[1].toInt()

            data.add(Move(direction, numberOfFields))
        }

        return data
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
