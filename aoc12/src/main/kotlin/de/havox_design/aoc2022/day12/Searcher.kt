package de.havox_design.aoc2022.day12

data class Searcher(
    val map: List<List<String>>,
    val currentPosition: Position,
    val landscape: Landscape,
    val step: Int = 0,
    var finished: Boolean = false
) {
    fun search(): List<Searcher> {
        return if (map[currentPosition.y][currentPosition.x] == "X") {
            finished = true
            listOf(this)
        } else {
            val nextToSearch = emptyList<Searcher>().toMutableList()

            nextToSearch += searchNorthOf(currentPosition)
            nextToSearch += searchSouthOf(currentPosition)
            nextToSearch += searchEastOf(currentPosition)
            nextToSearch += searchWestOf(currentPosition)

            nextToSearch
        }
    }

    private fun searchNorthOf(position: Position): List<Searcher> =
        searchInDirection(Position(position.x, position.y - 1), "^")

    private fun searchSouthOf(position: Position): List<Searcher> =
        searchInDirection(Position(position.x, position.y + 1), "v")

    private fun searchEastOf(position: Position): List<Searcher> =
        searchInDirection(Position(position.x + 1, position.y), ">")

    private fun searchWestOf(position: Position): List<Searcher> =
        searchInDirection(Position(position.x - 1, position.y), "<")

    private fun searchInDirection(position: Position, symbol: String): List<Searcher> {
        return if (landscape.canVisitFieldFrom(currentPosition, position)) {
            val newMap = map.toMutableList()
            val row = newMap[currentPosition.y].toMutableList()
            row[currentPosition.x] = symbol
            newMap[currentPosition.y] = row.toList()
            landscape.visit(position)

            val newSearcher = Searcher(newMap.toList(), position, landscape, step + 1)
            listOf(newSearcher)
        } else {
            emptyList()
        }
    }
}