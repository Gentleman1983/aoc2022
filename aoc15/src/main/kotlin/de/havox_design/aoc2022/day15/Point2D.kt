package de.havox_design.aoc2022.day15

import kotlin.math.absoluteValue

data class Point2D(val x: Int = 0, val y: Int = 0) {
    operator fun plus(other: Point2D): Point2D =
        Point2D(x + other.x, y + other.y)

    fun distanceTo(other: Point2D): Int =
        (x - other.x).absoluteValue + (y - other.y).absoluteValue
}
