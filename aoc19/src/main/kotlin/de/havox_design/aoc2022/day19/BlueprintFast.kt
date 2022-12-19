package de.havox_design.aoc2022.day19

data class BlueprintFast(
    var id: Int,
    var costOreForOreRobot: Int,
    var costOreForClayRobot: Int,
    var costOreForObsidianRobot: Int,
    var costClayForObsidianRobot: Int,
    var costOreForGeodeRobot: Int,
    var costObsidianForGeodeRobot: Int
) {
    companion object {
        fun getFor(blueprint: Blueprint): BlueprintFast =
            BlueprintFast(
                id = blueprint.id,
                costOreForOreRobot = blueprint.costOreRobot.ore,
                costOreForClayRobot = blueprint.costClayRobot.ore,
                costOreForObsidianRobot = blueprint.costObsidianRobot.ore,
                costClayForObsidianRobot = blueprint.costObsidianRobot.clay,
                costOreForGeodeRobot = blueprint.costGeodeRobot.ore,
                costObsidianForGeodeRobot = blueprint.costGeodeRobot.obsidian
            )
    }
}
