package de.havox_design.aoc2022.day09

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
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

    @ParameterizedTest
    @MethodSource("getDataForTailMovement")
    fun testTailMovement(grid: Grid, headStartRow:Int, headStartCol:Int, tailStartRow:Int, tailStartCol:Int, headMoveToRow:Int, headMoveToCol: Int, expectedTailRow: Int, expectedTailCol:Int) {
        grid.visitPosition(headStartRow, headStartCol, Knot.HEAD)
        grid.visitPosition(tailStartRow,tailStartCol, Knot.TAIL)
        grid.move(headStartRow, headStartCol, headMoveToRow, headMoveToCol)

        assertAll(
            {grid.getPosition(headMoveToRow, headMoveToCol).knot.shouldBe(Knot.HEAD)},
            {grid.getPosition(expectedTailRow, expectedTailCol).knot.shouldBe(Knot.TAIL)}
        )
    }

    companion object {
        @JvmStatic
        private fun getDataForTestVisitPosition(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Knot.HEAD, false),
                Arguments.of(Knot.TAIL, true)
            )

        @JvmStatic
        private fun getDataForTailMovement(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Grid(10,10),1,2,1,1,1,3,1,2),
                Arguments.of(Grid(10,10),2,1,1,1,3,1,2,1),
                Arguments.of(Grid(10,10),2,2,3,1,1,3,2,2),
                Arguments.of(Grid(10,10),2,2,3,1,2,3,2,2)
            )
    }
}

private fun Boolean.shouldBe(expectation: Boolean) = Assertions.assertEquals(expectation, this)
private fun Knot?.shouldBe(expectation: Knot?) = Assertions.assertEquals(expectation, this)
