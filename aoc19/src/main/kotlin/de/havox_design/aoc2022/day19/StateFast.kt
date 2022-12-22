package de.havox_design.aoc2022.day19

data class StateFast(
    var timeLeft: Int = 24,
    var moneyOre: Int = 0,
    var moneyClay: Int = 0,
    var moneyObsidian: Int = 0,
    var moneyGeode: Int = 0,
    var workersOre: Int = 1,
    var workersClay: Int = 0,
    var workersObsidian: Int = 0,
    var workersGeode: Int = 0
) : Comparable<StateFast> {

    fun handleMinute(): StateFast {
        timeLeft--
        collectProduction()
        return this
    }

    override fun compareTo(other: StateFast) =
        compareValuesBy(this, other) { it.heuristicScore() }

    private fun collectProduction() {
        moneyOre += workersOre
        moneyClay += workersClay
        moneyObsidian += workersObsidian
        moneyGeode += workersGeode
    }

    fun heuristicScore() =
        workersOre + workersClay + workersObsidian + workersGeode

    fun isBetterThan(other: StateFast): Boolean =
        moneyOre >= other.moneyOre
                && moneyClay >= other.moneyClay
                && moneyObsidian >= other.moneyObsidian
                && moneyGeode >= other.moneyGeode
                && workersOre >= other.workersOre
                && workersClay >= other.workersClay
                && workersObsidian >= other.workersObsidian
                && workersGeode >= other.workersGeode
}
