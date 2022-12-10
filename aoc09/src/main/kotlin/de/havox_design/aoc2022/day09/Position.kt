package de.havox_design.aoc2022.day09

data class Position(var knot: Knot? = null, var visitedByTail: Boolean = false) {
    fun visit(knot: Knot) {
        this.knot = if (knot == Knot.HEAD && this.knot == Knot.TAIL) {
            Knot.HEAD_AND_TAIL
        } else {
            knot
        }

        if (knot == Knot.TAIL || knot == Knot.HEAD_AND_TAIL) {
            visitedByTail = true
        }
    }

    fun leave() {
        knot = if (knot == Knot.HEAD_AND_TAIL) {
            Knot.TAIL
        } else {
            null
        }
    }
}
