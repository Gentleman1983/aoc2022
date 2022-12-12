package de.havox_design.aoc2022.day12

data class Field(var elevation: String = "", var visited: Boolean = false) {
    fun getElevation(): Int =
        HeightMapping.getElevationBySymbol(elevation)

    fun canVisitField(field: Field): Boolean =
        canVisitField(this, field)

    companion object {
        fun canVisitField(current: Field, target: Field): Boolean =
            target.getElevation() - current.getElevation() <= 1 && !target.visited
    }
}
