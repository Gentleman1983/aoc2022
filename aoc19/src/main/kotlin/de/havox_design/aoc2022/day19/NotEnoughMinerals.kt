package de.havox_design.aoc2022.day19

class NotEnoughMinerals(private var filename: String) {
    val blueprints = readFile()

    fun processPart1(): Int =
        blueprints
            .sumOf { blueprint -> BlueprintSimulation(blueprint).simulateBlueprint() }


    fun processPart2(leftBlueprints: Int = 3): Int =
        blueprints
            .slice(0 until leftBlueprints)
            .map { blueprint -> BlueprintSimulationFast(blueprint, 32).simulateBlueprint(false) }
            .reduce { a, b -> a * b }

    private fun readFile(): List<Blueprint> {
        val fileData = getResourceAsText(filename)

        return fileData.map { row ->
            Blueprint(
                id = row
                    .substringAfter("Blueprint ")
                    .substringBefore(":")
                    .toInt(),
                costOreRobot = RobotCurrency(
                    ore = row
                        .substringAfter("Each ore robot costs ")
                        .substringBefore(" ore. Each clay robot costs ")
                        .toInt()
                ),
                costClayRobot = RobotCurrency(
                    ore = row
                        .substringAfter("Each clay robot costs ")
                        .substringBefore(" ore. Each obsidian robot costs ")
                        .toInt()
                ),
                costObsidianRobot = RobotCurrency(
                    ore = row
                        .substringAfter("Each obsidian robot costs ")
                        .substringBefore(" ore and ") //NOSONAR reoccuring String is wanted for better readability
                        .toInt(),
                    clay = row
                        .substringAfter(" ore and ") //NOSONAR see before
                        .substringBefore(" clay. Each geode robot costs ")
                        .toInt()
                ),
                costGeodeRobot = RobotCurrency(
                    ore = row
                        .substringAfter("Each geode robot costs ")
                        .substringBeforeLast(" ore and ") //NOSONAR see before
                        .toInt(),
                    obsidian = row
                        .substringAfterLast(" ore and ") //NOSONAR see before
                        .substringBefore(" obsidian.")
                        .toInt()
                )
            )
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
