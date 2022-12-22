package de.havox_design.aoc2022.day19

data class State(
    var timeLeft: Int = 24,
    var money: RobotCurrency = RobotCurrency(),
    var workers: RobotWorkers = RobotWorkers(numberOreRobots = 1)
) : Comparable<State> {

    fun handleMinute(): State {
        timeLeft--
        money += workers.calculateResourceProduction()
        return this
    }

    override fun compareTo(other: State) =
        compareValuesBy(this, other) { it.heuristicScore() }

    fun heuristicScore() =
        workers.numberOreRobots + workers.numberClayRobots + workers.numberObsidianRobots + workers.numberGeodeRobots

    fun isBetterThan(other: State): Boolean =
        money >= other.money
                && workers >= other.workers
}
