package de.havox_design.aoc2022.day16

class ProboscideaVolcanium(private var filename: String) {
    val valves = readFile()
    private val usefulValves = valves.filter { valve -> valve.value.rate > 0 }
    private val distances = computeDistances()


    fun processPart1(): Int =
        traverse(minutes = 30)

    fun processPart2(): Int =
        traverse(minutes = 26, elephantGoesNext = true)

    @SuppressWarnings("kotlin:S6611")
    private fun traverse(
        minutes: Int,
        current: Valve = valves.getValue("AA"),
        remaining: Set<Valve> = usefulValves.values.toSet(),
        cache: MutableMap<State, Int> = mutableMapOf(),
        elephantGoesNext: Boolean = false
    ): Int {
        val currentScore = minutes * current.rate
        val currentState = State(current.name, minutes, remaining)

        return currentScore + cache.getOrPut(currentState) {
            val maxCurrent = remaining
                .filter { next -> distances[current.name]!![next.name]!! < minutes }
                .takeIf { it.isNotEmpty() }
                ?.maxOf { next ->
                    val remainingMinutes = minutes - 1 - distances[current.name]!![next.name]!!
                    traverse(remainingMinutes, next, remaining - next, cache, elephantGoesNext)
                }
                ?: 0
            maxOf(maxCurrent, if (elephantGoesNext) traverse(minutes = 26, remaining = remaining) else 0)
        }
    }

    @SuppressWarnings("kotlin:S6611")
    private fun computeDistances(): Map<String, Map<String, Int>> =
        valves
            .keys
            .map { valve ->
                val distances = mutableMapOf<String, Int>()
                    .withDefault { Int.MAX_VALUE }
                    .apply { put(valve, 0) }
                val toVisit = mutableListOf(valve)
                while (toVisit.isNotEmpty()) {
                    val current = toVisit.removeFirst()
                    valves[current]!!
                        .next
                        .forEach { neighbour ->
                            val newDistance = distances[current]!! + 1
                            if (newDistance < distances.getValue(neighbour)) {
                                distances[neighbour] = newDistance
                                toVisit.add(neighbour)
                            }
                        }
                }
                distances
            }.associateBy { it.keys.first() }

    private fun readFile(): Map<String, Valve> {
        val fileData = getResourceAsText(filename)

        return fileData
            .map(Valve::from)
            .associateBy(Valve::name)
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
