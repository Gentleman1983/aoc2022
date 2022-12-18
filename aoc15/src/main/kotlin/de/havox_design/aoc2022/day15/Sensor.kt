package de.havox_design.aoc2022.day15

import kotlin.math.absoluteValue

class Sensor(private val location: Point2D, closestBeacon: Point2D) {
    private val distance: Int = location.distanceTo(closestBeacon)

    fun findRange(y: Int): IntRange? {
        val scanWidth = distance - (location.y - y).absoluteValue
        return (location.x - scanWidth..location.x + scanWidth).takeIf { it.first <= it.last }
    }
}
