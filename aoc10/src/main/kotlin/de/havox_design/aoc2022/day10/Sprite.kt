package de.havox_design.aoc2022.day10

import kotlin.math.abs

data class Sprite(var position: Int) {
    fun getValueForPostion(position: Int): String =
        if (abs(this.position - position) <= 1) {
            "#"
        } else {
            "."
        }
}
