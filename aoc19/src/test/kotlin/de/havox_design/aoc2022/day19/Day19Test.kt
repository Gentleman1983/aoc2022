package de.havox_design.aoc2022.day19

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
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

    @ParameterizedTest
    @MethodSource("getDataForTestStateHandleMinute")
    fun testStateHandleMinute(
        state: State,
        expectedTimeLeft: Int,
        expectedRobots: RobotWorkers,
        expectedMoney: RobotCurrency
    ) {
        state.handleMinute()

        assertAll(
            { state.timeLeft.shouldBe(expectedTimeLeft) },
            { state.workers.shouldBe(expectedRobots) },
            { state.money.shouldBe(expectedMoney) }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForTestStateFastHandleMinute")
    fun testStateFastHandleMinute(
        state: StateFast,
        expectedTimeLeft: Int,
        expectedRobotsOre: Int,
        expectedRobotsClay: Int,
        expectedRobotsObsidian: Int,
        expectedRobotsGeode: Int,
        expectedMoneyOre: Int,
        expectedMoneyClay: Int,
        expectedMoneyObsidian: Int,
        expectedMoneyGeode: Int
    ) {
        state.handleMinute()

        assertAll(
            { state.timeLeft.shouldBe(expectedTimeLeft) },
            { state.workersOre.shouldBe(expectedRobotsOre) },
            { state.workersClay.shouldBe(expectedRobotsClay) },
            { state.workersObsidian.shouldBe(expectedRobotsObsidian) },
            { state.workersGeode.shouldBe(expectedRobotsGeode) },
            { state.moneyOre.shouldBe(expectedMoneyOre) },
            { state.moneyClay.shouldBe(expectedMoneyClay) },
            { state.moneyObsidian.shouldBe(expectedMoneyObsidian) },
            { state.moneyGeode.shouldBe(expectedMoneyGeode) }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForTestStateHeuristics")
    fun testStateHeuristics(state: State, expectedResult: Int) =
        state.heuristicScore().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestStateFastHeuristics")
    fun testStateFastHeuristics(state: StateFast, expectedResult: Int) =
        state.heuristicScore().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestStateCompare")
    fun testStateCompare(state: State, compareToState: State, expectedResult: Int) =
        state.compareTo(compareToState).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestStateFastCompare")
    fun testStateFastCompare(state: StateFast, compareToState: StateFast, expectedResult: Int) =
        state.compareTo(compareToState).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestStateIsBetterThan")
    fun testStateIsBetterThan(state: State, compareToState: State, expectedResultA: Boolean, expectedResultB: Boolean) =
        assertAll(
            { state.isBetterThan(compareToState).shouldBe(expectedResultA) },
            { compareToState.isBetterThan(state).shouldBe(expectedResultB) }
        )


    @ParameterizedTest
    @MethodSource("getDataForTestStateFastIsBetterThan")
    fun testStateFastIsBetterThan(
        state: StateFast,
        compareToState: StateFast,
        expectedResultA: Boolean,
        expectedResultB: Boolean
    ) =
        assertAll(
            { state.isBetterThan(compareToState).shouldBe(expectedResultA) },
            { compareToState.isBetterThan(state).shouldBe(expectedResultB) }
        )

    @ParameterizedTest
    @MethodSource("getDataForTestWorkersProduction")
    fun testWorkersProduction(worker: RobotWorkers, expectedResult: RobotCurrency) =
        worker.calculateResourceProduction().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestWorkersPlus")
    fun testWorkersPlus(workerA: RobotWorkers, workerB: RobotWorkers, expectedResult: RobotWorkers) =
        workerA.plus(workerB).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestWorkersCompare")
    fun testWorkersCompare(worker: RobotWorkers, compareToWorker: RobotWorkers, expectedResult: Int) =
        worker.compareTo(compareToWorker).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestCurrencyPlus")
    fun testCurrencyPlus(currencyA: RobotCurrency, currencyB: RobotCurrency, expectedResult: RobotCurrency) =
        currencyA.plus(currencyB).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestCurrencyMinus")
    fun testCurrencyMinus(currencyA: RobotCurrency, currencyB: RobotCurrency, expectedResult: RobotCurrency) =
        currencyA.minus(currencyB).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestCurrencyCompare")
    fun testCurrencyCompare(currency: RobotCurrency, compareToCurrency: RobotCurrency, expectedResult: Int) =
        currency.compareTo(compareToCurrency).shouldBe(expectedResult)


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

        @JvmStatic
        private fun getDataForTestStateHandleMinute(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    State(),
                    23,
                    RobotWorkers(1, 0, 0, 0),
                    RobotCurrency(1, 0, 0, 0)
                )
            )

        @JvmStatic
        private fun getDataForTestStateFastHandleMinute(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    StateFast(),
                    23,
                    1,
                    0,
                    0,
                    0,
                    1,
                    0,
                    0,
                    0
                )
            )

        @JvmStatic
        private fun getDataForTestStateHeuristics(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    State(),
                    1
                ),
                Arguments.of(
                    State(money = RobotCurrency(ore = 1, clay = 0, obsidian = 0, geode = 0)),
                    1
                ),
                Arguments.of(
                    State(money = RobotCurrency(ore = 0, clay = 1, obsidian = 0, geode = 0)),
                    1
                ),
                Arguments.of(
                    State(money = RobotCurrency(ore = 0, clay = 0, obsidian = 1, geode = 0)),
                    1
                ),
                Arguments.of(
                    State(money = RobotCurrency(ore = 0, clay = 0, obsidian = 0, geode = 1)),
                    1
                ),
                Arguments.of(
                    State(workers = RobotWorkers(0, 0, 0, 0)),
                    0
                ),
                Arguments.of(
                    State(workers = RobotWorkers(0, 1, 0, 0)),
                    1
                ),
                Arguments.of(
                    State(workers = RobotWorkers(0, 0, 1, 0)),
                    1
                ),
                Arguments.of(
                    State(workers = RobotWorkers(0, 0, 0, 1)),
                    1
                )
            )

        @JvmStatic
        private fun getDataForTestStateFastHeuristics(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    StateFast(),
                    1
                ),
                Arguments.of(
                    StateFast(moneyOre = 1),
                    1
                ),
                Arguments.of(
                    StateFast(moneyClay = 1),
                    1
                ),
                Arguments.of(
                    StateFast(moneyObsidian = 1),
                    1
                ),
                Arguments.of(
                    StateFast(moneyGeode = 1),
                    1
                ),
                Arguments.of(
                    StateFast(workersOre = 0),
                    0
                ),
                Arguments.of(
                    StateFast(workersOre = 0, workersClay = 1),
                    1
                ),
                Arguments.of(
                    StateFast(workersOre = 0, workersObsidian = 1),
                    1
                ),
                Arguments.of(
                    StateFast(workersOre = 0, workersGeode = 1),
                    1
                )
            )

        @JvmStatic
        private fun getDataForTestStateCompare(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    State(),
                    State(),
                    0
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 1, clay = 0, obsidian = 0, geode = 0)),
                    0
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 0, clay = 1, obsidian = 0, geode = 0)),
                    0
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 0, clay = 0, obsidian = 1, geode = 0)),
                    0
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 0, clay = 0, obsidian = 0, geode = 1)),
                    0
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 0, 0, 0)),
                    1
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 1, 0, 0)),
                    0
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 0, 1, 0)),
                    0
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 0, 0, 1)),
                    0
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(2, 0, 0, 0)),
                    -1
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(1, 1, 0, 0)),
                    -1
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(1, 0, 1, 0)),
                    -1
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(1, 0, 0, 1)),
                    -1
                )
            )

        @JvmStatic
        private fun getDataForTestStateFastCompare(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    StateFast(),
                    StateFast(),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyOre = 1),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyClay = 1),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyObsidian = 1),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyGeode = 1),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0),
                    1
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0, workersClay = 1),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0, workersObsidian = 1),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0, workersGeode = 1),
                    0
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 2),
                    -1
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 1, workersClay = 1),
                    -1
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 1, workersObsidian = 1),
                    -1
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 1, workersGeode = 1),
                    -1
                )
            )

        @JvmStatic
        private fun getDataForTestStateIsBetterThan(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    State(),
                    State(),
                    true,
                    true
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 1, clay = 0, obsidian = 0, geode = 0)),
                    false,
                    true
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 0, clay = 1, obsidian = 0, geode = 0)),
                    false,
                    true
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 0, clay = 0, obsidian = 1, geode = 0)),
                    false,
                    true
                ),
                Arguments.of(
                    State(),
                    State(money = RobotCurrency(ore = 0, clay = 0, obsidian = 0, geode = 1)),
                    false,
                    true
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 0, 0, 0)),
                    true,
                    false
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 1, 0, 0)),
                    false,
                    false
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 0, 1, 0)),
                    false,
                    false
                ),
                Arguments.of(
                    State(),
                    State(workers = RobotWorkers(0, 0, 0, 1)),
                    false,
                    false
                )
            )

        @JvmStatic
        private fun getDataForTestStateFastIsBetterThan(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    StateFast(),
                    StateFast(),
                    true,
                    true
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyOre = 1),
                    false,
                    true
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyClay = 1),
                    false,
                    true
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyObsidian = 1),
                    false,
                    true
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(moneyGeode = 1),
                    false,
                    true
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0),
                    true,
                    false
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0, workersClay = 1),
                    false,
                    false
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0, workersObsidian = 1),
                    false,
                    false
                ),
                Arguments.of(
                    StateFast(),
                    StateFast(workersOre = 0, workersGeode = 1),
                    false,
                    false
                )
            )

        @JvmStatic
        private fun getDataForTestWorkersProduction(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    RobotWorkers(1, 1, 1, 1),
                    RobotCurrency(1, 1, 1, 1)
                ),
                Arguments.of(
                    RobotWorkers(1, 0, 17, 23),
                    RobotCurrency(1, 0, 17, 23)
                ),
                Arguments.of(
                    RobotWorkers(5, 4, 3, 2),
                    RobotCurrency(5, 4, 3, 2)
                ),
                Arguments.of(
                    RobotWorkers(1, 0, 0, 0),
                    RobotCurrency(1, 0, 0, 0)
                ),
                Arguments.of(
                    RobotWorkers(0, 1, 0, 0),
                    RobotCurrency(0, 1, 0, 0)
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 1, 0),
                    RobotCurrency(0, 0, 1, 0)
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 1),
                    RobotCurrency(0, 0, 0, 1)
                )
            )

        @JvmStatic
        private fun getDataForTestWorkersPlus(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    RobotWorkers(1, 0, 0, 0),
                    RobotWorkers(1, 0, 0, 0),
                    RobotWorkers(2, 0, 0, 0)
                ),
                Arguments.of(
                    RobotWorkers(0, 1, 0, 0),
                    RobotWorkers(0, 1, 0, 0),
                    RobotWorkers(0, 2, 0, 0)
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 1, 0),
                    RobotWorkers(0, 0, 1, 0),
                    RobotWorkers(0, 0, 2, 0)
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 1),
                    RobotWorkers(0, 0, 0, 1),
                    RobotWorkers(0, 0, 0, 2)
                ),
                Arguments.of(
                    RobotWorkers(1, 2, 3, 4),
                    RobotWorkers(8, 7, 6, 5),
                    RobotWorkers(9, 9, 9, 9)
                )
            )

        @JvmStatic
        private fun getDataForTestWorkersCompare(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    RobotWorkers(0, 0, 0, 0),
                    RobotWorkers(0, 0, 0, 0),
                    0
                ),
                Arguments.of(
                    RobotWorkers(1, 0, 0, 0),
                    RobotWorkers(1, 0, 0, 0),
                    0
                ),
                Arguments.of(
                    RobotWorkers(0, 1, 0, 0),
                    RobotWorkers(0, 1, 0, 0),
                    0
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 1, 0),
                    RobotWorkers(0, 0, 1, 0),
                    0
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 1),
                    RobotWorkers(0, 0, 0, 1),
                    0
                ),
                Arguments.of(
                    RobotWorkers(1, 2, 3, 4),
                    RobotWorkers(1, 2, 3, 4),
                    0
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 0),
                    RobotWorkers(1, 0, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 0),
                    RobotWorkers(0, 1, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 0),
                    RobotWorkers(0, 0, 1, 0),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 0),
                    RobotWorkers(0, 0, 0, 1),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(0, 99, 99, 99),
                    RobotWorkers(1, 0, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(99, 0, 99, 99),
                    RobotWorkers(0, 1, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(99, 99, 0, 99),
                    RobotWorkers(0, 0, 1, 0),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(99, 99, 99, 0),
                    RobotWorkers(0, 0, 0, 1),
                    -1
                ),
                Arguments.of(
                    RobotWorkers(1, 0, 0, 0),
                    RobotWorkers(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotWorkers(0, 1, 0, 0),
                    RobotWorkers(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 1, 0),
                    RobotWorkers(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotWorkers(0, 0, 0, 1),
                    RobotWorkers(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotWorkers(1, 99, 99, 99),
                    RobotWorkers(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotWorkers(99, 2, 99, 99),
                    RobotWorkers(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotWorkers(99, 99, 3, 99),
                    RobotWorkers(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotWorkers(99, 99, 99, 4),
                    RobotWorkers(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotWorkers(99, 99, 99, 99),
                    RobotWorkers(1, 2, 3, 4),
                    1
                )
            )

        @JvmStatic
        private fun getDataForTestCurrencyPlus(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    RobotCurrency(1, 0, 0, 0),
                    RobotCurrency(1, 0, 0, 0),
                    RobotCurrency(2, 0, 0, 0)
                ),
                Arguments.of(
                    RobotCurrency(0, 1, 0, 0),
                    RobotCurrency(0, 1, 0, 0),
                    RobotCurrency(0, 2, 0, 0)
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 1, 0),
                    RobotCurrency(0, 0, 1, 0),
                    RobotCurrency(0, 0, 2, 0)
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 1),
                    RobotCurrency(0, 0, 0, 1),
                    RobotCurrency(0, 0, 0, 2)
                ),
                Arguments.of(
                    RobotCurrency(1, 2, 3, 4),
                    RobotCurrency(8, 7, 6, 5),
                    RobotCurrency(9, 9, 9, 9)
                )
            )

        @JvmStatic
        private fun getDataForTestCurrencyMinus(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    RobotCurrency(2, 0, 0, 0),
                    RobotCurrency(1, 0, 0, 0),
                    RobotCurrency(1, 0, 0, 0)
                ),
                Arguments.of(
                    RobotCurrency(0, 2, 0, 0),
                    RobotCurrency(0, 1, 0, 0),
                    RobotCurrency(0, 1, 0, 0)
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 2, 0),
                    RobotCurrency(0, 0, 1, 0),
                    RobotCurrency(0, 0, 1, 0)
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 2),
                    RobotCurrency(0, 0, 0, 1),
                    RobotCurrency(0, 0, 0, 1)
                ),
                Arguments.of(
                    RobotCurrency(9, 9, 9, 9),
                    RobotCurrency(1, 2, 3, 4),
                    RobotCurrency(8, 7, 6, 5)
                )
            )

        @JvmStatic
        private fun getDataForTestCurrencyCompare(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    RobotCurrency(0, 0, 0, 0),
                    RobotCurrency(0, 0, 0, 0),
                    0
                ),
                Arguments.of(
                    RobotCurrency(1, 0, 0, 0),
                    RobotCurrency(1, 0, 0, 0),
                    0
                ),
                Arguments.of(
                    RobotCurrency(0, 1, 0, 0),
                    RobotCurrency(0, 1, 0, 0),
                    0
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 1, 0),
                    RobotCurrency(0, 0, 1, 0),
                    0
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 1),
                    RobotCurrency(0, 0, 0, 1),
                    0
                ),
                Arguments.of(
                    RobotCurrency(1, 2, 3, 4),
                    RobotCurrency(1, 2, 3, 4),
                    0
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 0),
                    RobotCurrency(1, 0, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 0),
                    RobotCurrency(0, 1, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 0),
                    RobotCurrency(0, 0, 1, 0),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 0),
                    RobotCurrency(0, 0, 0, 1),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(0, 99, 99, 99),
                    RobotCurrency(1, 0, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(99, 0, 99, 99),
                    RobotCurrency(0, 1, 0, 0),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(99, 99, 0, 99),
                    RobotCurrency(0, 0, 1, 0),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(99, 99, 99, 0),
                    RobotCurrency(0, 0, 0, 1),
                    -1
                ),
                Arguments.of(
                    RobotCurrency(1, 0, 0, 0),
                    RobotCurrency(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotCurrency(0, 1, 0, 0),
                    RobotCurrency(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 1, 0),
                    RobotCurrency(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotCurrency(0, 0, 0, 1),
                    RobotCurrency(0, 0, 0, 0),
                    1
                ),
                Arguments.of(
                    RobotCurrency(1, 99, 99, 99),
                    RobotCurrency(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotCurrency(99, 2, 99, 99),
                    RobotCurrency(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotCurrency(99, 99, 3, 99),
                    RobotCurrency(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotCurrency(99, 99, 99, 4),
                    RobotCurrency(1, 2, 3, 4),
                    1
                ),
                Arguments.of(
                    RobotCurrency(99, 99, 99, 99),
                    RobotCurrency(1, 2, 3, 4),
                    1
                )
            )
    }
}

private fun Boolean.shouldBe(expectation: Boolean) = Assertions.assertEquals(expectation, this)
private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun RobotWorkers.shouldBe(expectation: RobotWorkers) = Assertions.assertEquals(expectation, this)
private fun RobotCurrency.shouldBe(expectation: RobotCurrency) = Assertions.assertEquals(expectation, this)
private fun List<*>.shouldBe(expectation: List<*>) = Assertions.assertEquals(expectation, this)
