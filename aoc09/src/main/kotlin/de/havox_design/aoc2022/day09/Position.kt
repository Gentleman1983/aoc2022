package de.havox_design.aoc2022.day09

data class Position(var knot: Knot? = null, var visitedByTail: Boolean = false) {
    fun visit(knot: Knot) {
        this.knot = knot

        if(knot == Knot.TAIL) {
            visitedByTail = true
        }
    }

    fun leave() {
        knot = null
    }
}
