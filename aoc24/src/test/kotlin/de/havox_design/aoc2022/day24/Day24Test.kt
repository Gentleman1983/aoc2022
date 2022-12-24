package de.havox_design.aoc2022.day24

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day24Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        BlizzardBasin(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(
        filename: String,
        expectedResult1stWayToGoal: Int,
        expectedResultWayBackToStart: Int,
        expectedResult2ndWayToGoal: Int,
        expectedResultTotal: Int
    ) =
        assertAll(
            { BlizzardBasin(filename).processPart2().get1stWayToGoalResult().shouldBe(expectedResult1stWayToGoal) },
            { BlizzardBasin(filename).processPart2().getWayBackToStartResult().shouldBe(expectedResultWayBackToStart) },
            { BlizzardBasin(filename).processPart2().get2ndWayToGoalResult().shouldBe(expectedResult2ndWayToGoal) },
            { BlizzardBasin(filename).processPart2().getTotalWayResult().shouldBe(expectedResultTotal) }
        )

    @ParameterizedTest
    @MethodSource("getDataForTestStartAndEnd")
    fun testStartAndEnd(filename: String, expectedStart: Position, expectedEnd: Position) =
        assertAll(
            { BlizzardBasin(filename).getStart().shouldBe(expectedStart) },
            { BlizzardBasin(filename).getEnd().shouldBe(expectedEnd) }
        )

    @ParameterizedTest
    @MethodSource("getDataForTestMovesDestinations")
    fun testMovesDestinations(move: Moves, expectedResult: Position) =
        move.getDirection(Position(0, 0)).shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sampleEasy.txt", 10),
                Arguments.of("sampleComplex.txt", 18)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sampleEasy.txt", 10, 10, 10, 30),
                Arguments.of("sampleComplex.txt", 18, 23, 13, 54)
            )

        @JvmStatic
        private fun getDataForTestStartAndEnd(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sampleEasy.txt", Position(1, 0), Position(5, 6)),
                Arguments.of("sampleComplex.txt", Position(1, 0), Position(6, 5))
            )

        @JvmStatic
        private fun getDataForTestMovesDestinations(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Moves.NORTH, Position(0, -1)),
                Arguments.of(Moves.EAST, Position(1, 0)),
                Arguments.of(Moves.SOUTH, Position(0, 1)),
                Arguments.of(Moves.WEST, Position(-1, 0)),
                Arguments.of(Moves.WAIT, Position(0, 0))
            )
    }
}

private fun Array<Int>.get1stWayToGoalResult(): Int = this[0]
private fun Array<Int>.getWayBackToStartResult(): Int = this[1]
private fun Array<Int>.get2ndWayToGoalResult(): Int = this[2]
private fun Array<Int>.getTotalWayResult(): Int = this[3]

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun Position.shouldBe(expectation: Position) = Assertions.assertEquals(expectation, this)
