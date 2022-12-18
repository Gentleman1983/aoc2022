package de.havox_design.aoc2022.day14

import kotlin.math.absoluteValue
import kotlin.math.sign

data class Point2D(val x: Int = 0, val y: Int = 0) {
    operator fun plus(other: Point2D): Point2D =
        Point2D(x + other.x, y + other.y)

    fun lineTo(other: Point2D): List<Point2D> {
        val deltaX = (other.x - x).sign
        val deltaY = (other.y - y).sign
        val steps = maxOf((x - other.x).absoluteValue, (y - other.y).absoluteValue)
        return (1..steps).scan(this) { last, _ -> Point2D(last.x + deltaX, last.y + deltaY) }
    }

    fun down(): Point2D =
        Point2D(x, y + 1)

    fun downLeft(): Point2D =
        Point2D(x - 1, y + 1)

    fun downRight(): Point2D =
        Point2D(x + 1, y + 1)

    companion object {
        fun of(input: String): Point2D =
            input.split(",").let { (x, y) -> Point2D(x.toInt(), y.toInt()) }
    }
}
