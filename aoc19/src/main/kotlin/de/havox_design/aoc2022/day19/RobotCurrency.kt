package de.havox_design.aoc2022.day19

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder

data class RobotCurrency(var ore: Int = 0, var clay: Int = 0, var obsidian: Int = 0, var geode: Int = 0) :
    Comparable<RobotCurrency> {
    operator fun plus(other: RobotCurrency): RobotCurrency =
        RobotCurrency(
            ore = this.ore + other.ore,
            clay = this.clay + other.clay,
            obsidian = this.obsidian + other.obsidian,
            geode = this.geode + other.geode
        )

    operator fun minus(other: RobotCurrency): RobotCurrency =
        RobotCurrency(
            ore = this.ore - other.ore,
            clay = this.clay - other.clay,
            obsidian = this.obsidian - other.obsidian,
            geode = this.geode - other.geode
        )

    override fun hashCode(): Int =
        HashCodeBuilder()
            .append(ore)
            .append(clay)
            .append(obsidian)
            .append(geode)
            .build()

    override fun equals(other: Any?): Boolean {
        return if (other is RobotCurrency) {
            EqualsBuilder()
                .append(this.ore, other.ore)
                .append(this.clay, other.clay)
                .append(this.obsidian, other.obsidian)
                .append(this.geode, other.geode)
                .build()
        } else {
            super.equals(other)
        }
    }

    override fun compareTo(other: RobotCurrency): Int =
        if (
            this.ore == other.ore
            && this.clay == other.clay
            && this.obsidian == other.obsidian
            && this.geode == other.geode
        ) {
            0
        } else if (
            this.ore >= other.ore
            && this.clay >= other.clay
            && this.obsidian >= other.obsidian
            && this.geode >= other.geode
        ) {
            1
        } else {
            -1
        }
}
