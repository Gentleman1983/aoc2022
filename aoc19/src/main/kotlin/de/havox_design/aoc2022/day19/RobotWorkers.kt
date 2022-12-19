package de.havox_design.aoc2022.day19

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder

data class RobotWorkers(
    var numberOreRobots: Int = 0,
    var numberClayRobots: Int = 0,
    var numberObsidianRobots: Int = 0,
    var numberGeodeRobots: Int = 0
) : Comparable<RobotWorkers> {
    operator fun plus(other: RobotWorkers): RobotWorkers =
        RobotWorkers(
            numberOreRobots = this.numberOreRobots + other.numberOreRobots,
            numberClayRobots = this.numberClayRobots + other.numberClayRobots,
            numberObsidianRobots = this.numberObsidianRobots + other.numberObsidianRobots,
            numberGeodeRobots = this.numberGeodeRobots + other.numberGeodeRobots
        )

    fun calculateResourceProduction(): RobotCurrency =
        RobotCurrency(
            ore = numberOreRobots,
            clay = numberClayRobots,
            obsidian = numberObsidianRobots,
            geode = numberGeodeRobots
        )

    override fun hashCode(): Int =
        HashCodeBuilder()
            .append(numberOreRobots)
            .append(numberClayRobots)
            .append(numberObsidianRobots)
            .append(numberGeodeRobots)
            .build()

    override fun equals(other: Any?): Boolean {
        return if (other is RobotWorkers) {
            EqualsBuilder()
                .append(this.numberOreRobots, other.numberOreRobots)
                .append(this.numberClayRobots, other.numberClayRobots)
                .append(this.numberObsidianRobots, other.numberObsidianRobots)
                .append(this.numberGeodeRobots, other.numberGeodeRobots)
                .build()
        } else {
            super.equals(other)
        }
    }

    override fun compareTo(other: RobotWorkers): Int =
        if (
            this.numberOreRobots == other.numberOreRobots
            && this.numberClayRobots == other.numberClayRobots
            && this.numberObsidianRobots == other.numberObsidianRobots
            && this.numberGeodeRobots == other.numberGeodeRobots
        ) {
            0
        } else if (
            this.numberOreRobots >= other.numberOreRobots
            && this.numberClayRobots >= other.numberClayRobots
            && this.numberObsidianRobots >= other.numberObsidianRobots
            && this.numberGeodeRobots >= other.numberGeodeRobots
        ) {
            1
        } else {
            -1
        }
}
