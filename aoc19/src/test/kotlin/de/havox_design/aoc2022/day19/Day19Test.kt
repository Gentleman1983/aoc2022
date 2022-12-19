package de.havox_design.aoc2022.day19

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day19Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf("testing"))
    }

    @Disabled
    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        NotEnoughMinerals(filename).processPart1().shouldBe(expectedResult)

    @Disabled
    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        NotEnoughMinerals(filename).processPart2(2).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestReadFile")
    fun testReadFile(filename: String, expectedResult: List<Blueprint>) =
        NotEnoughMinerals(filename).blueprints.shouldBe(expectedResult)

    @Disabled
    @ParameterizedTest
    @MethodSource("getDataForTestBlueprintSimulation")
    fun testBlueprintSimulation(filename: String, blueprintId: Int, minutes: Int, expectedQualityLevel: Int) =
        BlueprintSimulation(
            blueprint = NotEnoughMinerals(filename)
                .blueprints
                .first { blueprint -> blueprint.id == blueprintId },
            minutes = minutes
        )
            .simulateBlueprint()
            .shouldBe(expectedQualityLevel)

    @Disabled
    @ParameterizedTest
    @MethodSource("getDataForTestBlueprintSimulation")
    fun testBlueprintSimulationFast(filename: String, blueprintId: Int, minutes: Int, expectedQualityLevel: Int) =
        BlueprintSimulationFast(
            blueprint = NotEnoughMinerals(filename)
                .blueprints
                .first { blueprint -> blueprint.id == blueprintId },
            minutes = minutes
        )
            .simulateBlueprint()
            .shouldBe(expectedQualityLevel)

    @Test
    fun verifyEqualsContractOnRobotCurrencyClass() =
        EqualsVerifier
            .simple()
            .forClass(RobotCurrency::class.java)
            .suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT)
            .verify()

    @Test
    fun verifyEqualsContractOnRobotWorkersClass() =
        EqualsVerifier
            .simple()
            .forClass(RobotWorkers::class.java)
            .suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT)
            .verify()

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 33)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 56)
            )

        @JvmStatic
        private fun getDataForTestReadFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    listOf(
                        Blueprint(
                            id = 1,
                            costOreRobot = RobotCurrency(ore = 4),
                            costClayRobot = RobotCurrency(ore = 2),
                            costObsidianRobot = RobotCurrency(ore = 3, clay = 14),
                            costGeodeRobot = RobotCurrency(ore = 2, obsidian = 7)
                        ),
                        Blueprint(
                            id = 2,
                            costOreRobot = RobotCurrency(ore = 2),
                            costClayRobot = RobotCurrency(ore = 3),
                            costObsidianRobot = RobotCurrency(ore = 3, clay = 8),
                            costGeodeRobot = RobotCurrency(ore = 3, obsidian = 12)
                        )
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestBlueprintSimulation(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    1,
                    24,
                    9
                ),
                Arguments.of(
                    "sample.txt",
                    2,
                    24,
                    24
                )
            )
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun List<*>.shouldBe(expectation: List<*>) = Assertions.assertEquals(expectation, this)
