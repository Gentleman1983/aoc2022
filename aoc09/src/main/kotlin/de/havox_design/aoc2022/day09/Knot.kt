package de.havox_design.aoc2022.day09

data class Knot(var name: String) {
    companion object {
        var HEAD = Knot("H")
        var TAIL = Knot("T")
        var HEAD_AND_TAIL = Knot("H&T")
    }
}
