package de.havox_design.aoc2022.day15

class BeaconExclusionZone(private var filename: String) {
    private val data = readFile()

    fun processPart1(desiredRow: Int): Int =
        data.mapNotNull { it.findRange(desiredRow) }
            .reduce()
            .sumOf { it.last - it.first }

    fun processPart2(caveSize: Int): Long {
        val cave = (0..caveSize)

        return data.firstNotNullOf { sensor ->
            val up = Point2D(sensor.location.x, sensor.location.y - sensor.distance - 1)
            val right = Point2D(sensor.location.x + sensor.distance + 1, sensor.location.y)
            val down = Point2D(sensor.location.x, sensor.location.y + sensor.distance + 1)
            val left = Point2D(sensor.location.x - sensor.distance - 1, sensor.location.y)

            (up.lineTo(right) + right.lineTo(down) + down.lineTo(left) + left.lineTo(up))
                .filter { location -> location.x in cave && location.y in cave }
                .firstOrNull { possibleLocation -> data.none { sensor -> sensor.isInRange(possibleLocation) } }
        }.calculateTuningFrequency()
    }

    private fun readFile(): Set<Sensor> {
        val fileData = getResourceAsText(filename)

        return fileData.map { row ->
            Sensor(
                Point2D(
                    row
                        .substringAfter("x=")
                        .substringBefore(",")
                        .toInt(),
                    row
                        .substringAfter("y=")
                        .substringBefore(":")
                        .toInt()
                ),
                Point2D(
                    row
                        .substringAfterLast("x=")
                        .substringBefore(",")
                        .toInt(),
                    row
                        .substringAfterLast("y=")
                        .toInt()
                )
            )
        }.toSet()
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()

    private fun List<IntRange>.reduce(): List<IntRange> =
        if (this.size <= 1) this
        else {
            val sorted = this.sortedBy { it.first }
            sorted
                .drop(1)
                .fold(mutableListOf(sorted.first())) { reduced, range ->
                    val lastRange = reduced.last()
                    if (range.first <= lastRange.last)
                        reduced[reduced.lastIndex] = (lastRange.first..maxOf(lastRange.last, range.last))
                    else
                        reduced.add(range)
                    reduced
                }
        }
}
