package de.havox_design.aoc2022.day12

data class Landscape(val landscapeData: List<String>) {
    val map = emptyMap<Position, Field>().toMutableMap()

    init {
        for (rowIndex in landscapeData.indices) {
            for (colIndex in landscapeData[rowIndex].indices) {
                map[Position(colIndex, rowIndex)] = Field(landscapeData[rowIndex].substring(colIndex, colIndex + 1))
            }
        }
    }

    fun canVisitFieldFrom(source: Position, target: Position): Boolean {
        return if (map.containsKey(source) && map.containsKey(target)) {
            val startField = map[source]
            val targetField = map[target]

            startField!!.canVisitField(targetField!!)
        } else {
            false
        }
    }

    fun get(pos: Position): Field? = map[pos]

    @SuppressWarnings("kotlin:S6611")
    fun visit(pos: Position) {
        if (map.containsKey(pos)) {
            map[pos]!!.visited = true
        }
    }
}
