package de.havox_design.aoc2022.day09

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class Day09Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestVisitPosition")
    fun testVisitPosition(knot: Knot, expectedResult: Boolean) {
        var position = Position()
        position.visit(knot)

        assertAll(
            { position.knot.shouldBe(knot) },
            { position.visitedByTail.shouldBe(expectedResult) }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForTestVisitPosition")
    fun testLeavePosition(knot: Knot, expectedResult: Boolean) {
        var position = Position()
        position.visit(knot)
        position.leave()

        assertAll(
            { position.knot.shouldBe(null) },
            { position.visitedByTail.shouldBe(expectedResult) }
        )
    }

    companion object {
        @JvmStatic
        private fun getDataForTestVisitPosition(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Knot.HEAD, false),
                Arguments.of(Knot.TAIL, true)
            )
    }
}

private fun Boolean.shouldBe(expectation: Boolean) = Assertions.assertEquals(expectation, this)
private fun Knot?.shouldBe(expectation: Knot?) = Assertions.assertEquals(expectation, this)
