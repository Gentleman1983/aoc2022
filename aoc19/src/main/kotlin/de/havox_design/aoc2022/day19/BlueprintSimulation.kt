package de.havox_design.aoc2022.day19

import java.util.PriorityQueue

class BlueprintSimulation(var blueprint: Blueprint, var minutes: Int = 24) {
    fun simulateBlueprint(calculateQuality: Boolean = true): Int =
        if (calculateQuality) {
            findMaxNumberOfGeodes(blueprint, minutes) * blueprint.id
        } else {
            findMaxNumberOfGeodes(blueprint, minutes)
        }

    private fun findMaxNumberOfGeodes(blueprint: Blueprint, initialTimeLeft: Int): Int {
        val queue = ArrayDeque<State>()
        queue.add(State(initialTimeLeft))

        val bestRobots = PriorityQueue<State>()
        val visited: MutableSet<State> = mutableSetOf()

        fun addState(state: State) {
            if (state in visited) {
                return
            }
            visited.add(state)
            for (robot in bestRobots) {
                if (robot.isBetterThan(state)) {
                    return
                }
            }
            bestRobots.add(state)
            if (bestRobots.size > 500) {
                bestRobots.poll()
            }
            queue.add(state)
        }

        var best = 0
        while (queue.isNotEmpty()) {
            val state = queue.removeFirst()
            val minute = state.copy().handleMinute()
            if (minute.timeLeft == 0) {
                best = maxOf(best, minute.money.geode)
                continue
            }
            if (state.money >= blueprint.costGeodeRobot)
                minute.copy(
                    money = minute.money - blueprint.costGeodeRobot,
                    workers = minute.workers + RobotWorkers(numberGeodeRobots = 1)
                ).run(::addState)
            if (state.money >= blueprint.costObsidianRobot)
                minute.copy(
                    money = minute.money - blueprint.costObsidianRobot,
                    workers = minute.workers + RobotWorkers(numberObsidianRobots = 1)
                ).run(::addState)
            if (state.money >= blueprint.costClayRobot) {
                minute.copy(
                    money = minute.money - blueprint.costClayRobot,
                    workers = minute.workers + RobotWorkers(numberClayRobots = 1)
                ).run(::addState)
            }
            if (state.money >= blueprint.costOreRobot) {
                minute.copy(
                    money = minute.money - blueprint.costOreRobot,
                    workers = minute.workers + RobotWorkers(numberOreRobots = 1)
                ).run(::addState)
            }
            addState(minute.copy())
        }
        return best
    }
}
