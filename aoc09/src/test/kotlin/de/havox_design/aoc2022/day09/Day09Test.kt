package de.havox_design.aoc2022.day09

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
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
    fun testTailMovement(
        grid: Grid,
        headStartRow: Int,
        headStartCol: Int,
        tailStartRow: Int,
        tailStartCol: Int,
        direction: Direction,
        expectedTailRow: Int,
        expectedTailCol: Int
    ) {
        grid.visitPosition(headStartRow, headStartCol, Knot.HEAD)
        grid.visitPosition(tailStartRow, tailStartCol, Knot.TAIL)
        grid.move(headStartRow, headStartCol, direction)

        val headMoveToRow = headStartRow + direction.modRow
        val headMoveToCol = headStartCol + direction.modCol

        assertAll(
            { grid.getPosition(headMoveToRow, headMoveToCol).knot.shouldBe(Knot.HEAD) },
            { grid.getPosition(expectedTailRow, expectedTailCol).knot.shouldBe(Knot.TAIL) }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForFindDirectionBySymbol")
    fun findDirectionBySymbol(symbol: String, expectedDirection: Direction) =
        Direction.findDirectionBySymbol(symbol).shouldBe(expectedDirection)

    @ParameterizedTest
    @MethodSource("getDataForFindDirectionByMovement")
    fun findDirectionByMovement(row: Int, col: Int, expectedDirection: Direction) =
        Direction.findDirectionByMovement(row, col).shouldBe(expectedDirection)

    @ParameterizedTest
    @MethodSource("getDataForReadFile")
    fun readFile(filename: String, expectedMoves: List<Move>) {
        val objectUnderTest = RopeBridge(filename)

        assertAll(
            { objectUnderTest.moves.shouldContainAll(expectedMoves) },
            { objectUnderTest.moves.shouldBe(expectedMoves) }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForTestPart1")
    fun testPart1(filename: String, expectedVisitedFields: Int) {
        RopeBridge(filename).processPart1().shouldBe(expectedVisitedFields)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestPart2")
    fun testPart2(filename: String, expectedVisitedFields: Int) {
        RopeBridge(filename).processPart2().shouldBe(expectedVisitedFields)
    }

    @Test
    fun verifyEqualsContractOnGridClass() {
        EqualsVerifier.forClass(Grid.Companion::class.java).suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT).verify()
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
                Arguments.of(Grid(10, 10), 1, 2, 1, 1, Direction.RIGHT, 1, 2),
                Arguments.of(Grid(10, 10), 2, 1, 1, 1, Direction.DOWN, 2, 1),
                Arguments.of(Grid(10, 10), 2, 2, 3, 1, Direction.UP, 2, 2),
                Arguments.of(Grid(10, 10), 2, 2, 3, 1, Direction.RIGHT, 2, 2)
            )

        @JvmStatic
        private fun getDataForFindDirectionBySymbol(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Direction.UP_LEFT.symbol, Direction.UP_LEFT),
                Arguments.of(Direction.UP.symbol, Direction.UP),
                Arguments.of(Direction.UP_RIGHT.symbol, Direction.UP_RIGHT),
                Arguments.of(Direction.RIGHT.symbol, Direction.RIGHT),
                Arguments.of(Direction.DOWN_RIGHT.symbol, Direction.DOWN_RIGHT),
                Arguments.of(Direction.DOWN.symbol, Direction.DOWN),
                Arguments.of(Direction.DOWN_LEFT.symbol, Direction.DOWN_LEFT),
                Arguments.of(Direction.LEFT.symbol, Direction.LEFT),
                Arguments.of(Direction.UNKNOWN.symbol, Direction.UNKNOWN),
                Arguments.of("foo", Direction.UNKNOWN),
                Arguments.of("bar", Direction.UNKNOWN)
            )

        @JvmStatic
        private fun getDataForFindDirectionByMovement(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Direction.UP_LEFT.modRow, Direction.UP_LEFT.modCol, Direction.UP_LEFT),
                Arguments.of(Direction.UP.modRow, Direction.UP.modCol, Direction.UP),
                Arguments.of(Direction.UP_RIGHT.modRow, Direction.UP_RIGHT.modCol, Direction.UP_RIGHT),
                Arguments.of(Direction.RIGHT.modRow, Direction.RIGHT.modCol, Direction.RIGHT),
                Arguments.of(Direction.DOWN_RIGHT.modRow, Direction.DOWN_RIGHT.modCol, Direction.DOWN_RIGHT),
                Arguments.of(Direction.DOWN.modRow, Direction.DOWN.modCol, Direction.DOWN),
                Arguments.of(Direction.DOWN_LEFT.modRow, Direction.DOWN_LEFT.modCol, Direction.DOWN_LEFT),
                Arguments.of(Direction.LEFT.modRow, Direction.LEFT.modCol, Direction.LEFT),
                Arguments.of(Direction.UNKNOWN.modRow, Direction.UNKNOWN.modCol, Direction.UNKNOWN),
                Arguments.of(47, 11, Direction.UNKNOWN),
                Arguments.of(-8, 15, Direction.UNKNOWN)
            )

        @JvmStatic
        private fun getDataForReadFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    listOf(
                        Move(Direction.RIGHT, 4),
                        Move(Direction.UP, 4),
                        Move(Direction.LEFT, 3),
                        Move(Direction.DOWN, 1),
                        Move(Direction.RIGHT, 4),
                        Move(Direction.DOWN, 1),
                        Move(Direction.LEFT, 5),
                        Move(Direction.RIGHT, 2)
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 13)
            )

        @JvmStatic
        private fun getDataForTestPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 1),
                Arguments.of("sample2.txt", 36)
            )
    }
}

private fun Boolean.shouldBe(expectation: Boolean) = Assertions.assertEquals(expectation, this)
private fun Direction.shouldBe(expectation: Direction) = Assertions.assertEquals(expectation, this)
private fun Knot?.shouldBe(expectation: Knot?) = Assertions.assertEquals(expectation, this)
private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun Collection<*>?.shouldBe(expectation: Collection<*>?) = Assertions.assertEquals(expectation, this)
private fun Collection<*>.shouldContainAll(expectation: Collection<*>) =
    Assertions.assertTrue(this.containsAll(expectation))
