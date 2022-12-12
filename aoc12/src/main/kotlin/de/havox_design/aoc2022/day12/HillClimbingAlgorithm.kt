package de.havox_design.aoc2022.day12

class HillClimbingAlgorithm(private var filename: String) {
    val landscape = readFile()
    private var movementMap = parseMovementMap()

    fun processPart1(): Int {
        val startingPosition = findStartingPosition()
        landscape.visit(startingPosition)

        var searchers = listOf(Searcher(movementMap, startingPosition, landscape))


        while( searchers.none { searcher -> searcher.finished } ) {
            val nextIteration = emptyList<Searcher>().toMutableList()

            for(searcher in searchers) {
                nextIteration += searcher.search()
            }

            searchers = nextIteration
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

    private fun findStartingPosition(): Position {
        for(entry in landscape.map.entries) {
            if(entry.value.elevation == "S") {
                return entry.key
            }
        }

        return Position(-666, -666)
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
