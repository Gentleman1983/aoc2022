package de.havox_design.aoc2022.day09

class RopeBridge(private var filename: String) {
    val moves = readFile()

    fun processPart1(): Int {
        var fieldsVisited = 0
        val rows = 1000
        val cols = 1000

        val grid = Grid(rows, cols)
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

    fun processPart2(): Int {
        var fieldsVisited = 0
        val rows = 1000
        val cols = 1000

        val gridSegmentH1 = Grid(rows, cols)
        val gridSegment12 = Grid(rows, cols)
        val gridSegment23 = Grid(rows, cols)
        val gridSegment34 = Grid(rows, cols)
        val gridSegment45 = Grid(rows, cols)
        val gridSegment56 = Grid(rows, cols)
        val gridSegment67 = Grid(rows, cols)
        val gridSegment78 = Grid(rows, cols)
        val gridSegment89 = Grid(rows, cols)
        gridSegmentH1.visitPosition(rows / 2, cols / 2, Knot.HEAD_AND_TAIL)
        gridSegment12.visitPosition(rows / 2, cols / 2, Knot.HEAD_AND_TAIL)
        gridSegment23.visitPosition(rows / 2, cols / 2, Knot.HEAD_AND_TAIL)
        gridSegment34.visitPosition(rows / 2, cols / 2, Knot.HEAD_AND_TAIL)
        gridSegment45.visitPosition(rows / 2, cols / 2, Knot.HEAD_AND_TAIL)
        gridSegment56.visitPosition(rows / 2, cols / 2, Knot.HEAD_AND_TAIL)
        gridSegment67.visitPosition(rows / 2, cols / 2, Knot.HEAD_AND_TAIL)
        gridSegment78.visitPosition(rows / 2, cols / 2, Knot.HEAD_AND_TAIL)
        gridSegment89.visitPosition(rows / 2, cols / 2, Knot.HEAD_AND_TAIL)

        for (move in moves) {
            val direction = move.direction
            val fields = move.numberOfFields

            for (step in 0 until fields) {
                var d = gridSegmentH1.moveHead(Move(direction))
                d = gridSegment12.moveHead(Move(d))
                d = gridSegment23.moveHead(Move(d))
                d = gridSegment34.moveHead(Move(d))
                d = gridSegment45.moveHead(Move(d))
                d = gridSegment56.moveHead(Move(d))
                d = gridSegment67.moveHead(Move(d))
                d = gridSegment78.moveHead(Move(d))
                gridSegment89.moveHead(Move(d))
            }
        }

        for (row in 0 until gridSegment89.rows) {
            for (col in 0 until gridSegment89.cols) {
                if (gridSegment89.getPosition(row, col).visitedByTail) {
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
