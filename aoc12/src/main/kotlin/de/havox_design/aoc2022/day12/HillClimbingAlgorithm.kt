package de.havox_design.aoc2022.day12

class HillClimbingAlgorithm(private var filename: String) {
    val landscape = readFile()
    private var movementMap = parseMovementMap()

    fun processPart1(): Int =
        run(findStartingPosition())

    fun processPart2(): Int {
        val startingPositions = findStartingPositions("S", "a")

        return startingPositions
            .parallelStream()
            .mapToInt { startingPosition -> HillClimbingAlgorithm(filename).run(startingPosition) }
            .min()
            .asInt
    }

    private fun run(startingPosition: Position): Int {
        landscape.visit(startingPosition)

        var searchers = listOf(Searcher(movementMap, startingPosition, landscape))


        while (searchers.none { searcher -> searcher.finished }) {
            val nextIteration = emptyList<Searcher>().toMutableList()

            for (searcher in searchers) {
                nextIteration += searcher.search()
            }

            // If we are lost quit
            searchers = nextIteration
            if (searchers.isEmpty()) {
                return Int.MAX_VALUE
            }
        }

        return searchers.first { searcher -> searcher.finished }.step
    }

    private fun readFile(): Landscape {
        val fileInput = getResourceAsText(filename)

        return Landscape(fileInput)
    }

    private fun parseMovementMap(): List<List<String>> {
        val data = getResourceAsText(filename)
        val movementMap = emptyList<List<String>>().toMutableList()

        for (rowIndex in data.indices) {
            val row = data[rowIndex]
            var rowList = emptyList<String>()

            for (colIndex in row.indices) {
                rowList += if (row.substring(colIndex, colIndex + 1) == "E") "X" else "."
            }

            movementMap += rowList
        }

        return movementMap.toList()
    }

    private fun findStartingPosition(): Position =
        findStartingPositions("S")[0]

    private fun findStartingPositions(vararg symbols: String): List<Position> {
        val startingPositions = emptyList<Position>().toMutableList()

        for (entry in landscape.map.entries) {
            if (symbols.contains(entry.value.elevation)) {
                startingPositions += entry.key
            }
        }

        return startingPositions
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
