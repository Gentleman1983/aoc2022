package de.havox_design.aoc2022.day19

import java.util.PriorityQueue

class BlueprintSimulationFast(var blueprint: Blueprint, var minutes: Int = 24) {
    fun simulateBlueprint(calculateQuality: Boolean = true): Int =
        if (calculateQuality) {
            findMaxNumberOfGeodes(BlueprintFast.getFor(blueprint), minutes) * blueprint.id
        } else {
            findMaxNumberOfGeodes(BlueprintFast.getFor(blueprint), minutes)
        }

    private fun findMaxNumberOfGeodes(blueprint: BlueprintFast, initialTimeLeft: Int): Int {
        val queue = ArrayDeque<StateFast>()
        queue.add(StateFast(timeLeft = initialTimeLeft))

        val bestRobots = PriorityQueue<StateFast>()
        val visited: MutableSet<Int> = mutableSetOf()

        fun addState(state: StateFast) {
            if (state.hashCode() in visited) {
                return
            }
            visited.add(state.hashCode())
            for (robot in bestRobots) {
                if (robot.isBetterThan(state)) {
                    return
                }
            }
            bestRobots.add(state)
            if (bestRobots.size > 250) {
                bestRobots.poll()
            }
            queue.add(state)
        }

        var best = 0
        while (queue.isNotEmpty()) {
            val state = queue.removeFirst()
            val minute = state.copy().handleMinute()
            if (minute.timeLeft == 0) {
                best = maxOf(best, minute.moneyGeode)
                continue
            }
            if (state.moneyOre >= blueprint.costOreForGeodeRobot
                && state.moneyObsidian >= blueprint.costObsidianForGeodeRobot
            )
                minute.copy(
                    moneyOre = minute.moneyOre - blueprint.costOreForGeodeRobot,
                    moneyObsidian = minute.moneyObsidian - blueprint.costObsidianForGeodeRobot,
                    workersGeode = minute.workersGeode + 1
                ).run(::addState)
            if (state.moneyOre >= blueprint.costOreForObsidianRobot
                && state.moneyClay >= blueprint.costClayForObsidianRobot
            )
                minute.copy(
                    moneyOre = minute.moneyOre - blueprint.costOreForObsidianRobot,
                    moneyClay = minute.moneyClay - blueprint.costClayForObsidianRobot,
                    workersObsidian = minute.workersObsidian + 1
                ).run(::addState)
            if (state.moneyOre >= blueprint.costOreForClayRobot) {
                minute.copy(
                    moneyOre = minute.moneyOre - blueprint.costOreForClayRobot,
                    workersClay = minute.workersClay + 1
                ).run(::addState)
            }
            if (state.moneyOre >= blueprint.costOreForOreRobot) {
                minute.copy(
                    moneyOre = minute.moneyOre - blueprint.costOreForOreRobot,
                    workersOre = minute.workersOre + 1
                ).run(::addState)
            }
            addState(minute.copy())
        }
        return best
    }
}
