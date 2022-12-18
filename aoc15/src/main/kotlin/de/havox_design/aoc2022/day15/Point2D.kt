package de.havox_design.aoc2022.day15

import kotlin.math.absoluteValue
import kotlin.math.sign

data class Point2D(val x: Int = 0, val y: Int = 0) {
    operator fun plus(other: Point2D): Point2D =
        Point2D(x + other.x, y + other.y)

    fun distanceTo(other: Point2D): Int =
        (x - other.x).absoluteValue + (y - other.y).absoluteValue

    fun lineTo(other: Point2D): List<Point2D> {
        val deltaX = (other.x - x).sign
        val deltaY = (other.y - y).sign
        val steps = maxOf((x - other.x).absoluteValue, (y - other.y).absoluteValue)
        return (1..steps).scan(this) { last, _ -> Point2D(last.x + deltaX, last.y + deltaY) }
    }

    fun calculateTuningFrequency(): Long = (x * 4000000L) + y
}
