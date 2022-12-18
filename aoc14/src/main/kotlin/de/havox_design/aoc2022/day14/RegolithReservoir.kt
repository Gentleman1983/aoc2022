package de.havox_design.aoc2022.day14

class RegolithReservoir(private var filename: String) {
    private val cave = readFile()
    private val sandSource = Point2D(500, 0)
    private val maxY = cave.maxOf { it.y }

    fun processPart1(): Int =
        dropSand(maxY + 1)

    fun processPart2(): Int {
        val minX = cave.minOf { it.x }
        val maxX = cave.maxOf { it.x }
        cave
            .addAll(Point2D(minX - maxY, maxY + 2)
                .lineTo(Point2D(maxX + maxY, maxY + 2))
            )
        return dropSand(maxY + 3) + 1
    }

    private fun readFile(): MutableSet<Point2D> {
        val fileData = getResourceAsText(filename)

        return fileData.flatMap { row ->
            row.split(" -> ")
                .map { Point2D.of(it) }
                .zipWithNext()
                .flatMap { (from, to) ->
                    from.lineTo(to)
                }
        }.toMutableSet()
    }

    private fun dropSand(voidStartsAt: Int): Int {
        var start = sandSource
        var landed = 0
        while (true) {
            val next = listOf(start.down(), start.downLeft(), start.downRight()).firstOrNull { it !in cave }
            start = when {
                next == null && start == sandSource -> return landed
                next == null -> {
                    cave.add(start)
                    landed += 1
                    sandSource
                }

                next.y == voidStartsAt -> return landed
                else -> next
            }
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
