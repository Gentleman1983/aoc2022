package de.havox_design.aoc2022.day09

class RopeBridge(private var filename: String) {
    val moves = readFile()

    fun processPart1(): Int {
        var fieldsVisited = 0
        val rows = 1000
        val cols = 1000

        var grid = Grid(rows, cols)
        grid.visitPosition(rows / 2, cols / 2, Knot.HEAD_AND_TAIL)

        for (move in moves) {
            grid.moveHead(move)
        }

        for (row in 0 until grid.rows) {
            for (col in 0 until grid.cols) {
                if (grid.getPosition(row, col).visitedByTail) {
                    fieldsVisited++
                }
            }
        }

        return fieldsVisited
    }

    private fun readFile(): List<Move> {
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
