package de.havox_design.aoc2022.day22

import de.havox_design.aoc2022.day22.Map
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day22Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        MonkeyMap(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(expectedResult: Int) =
        MonkeyMap("").processPart2().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestReadOrders")
    fun testReadOrders(filename: String, expectedResult: List<String>) =
        MonkeyMap(filename).orders.shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestReadMapData")
    fun testReadMapData(filename: String, expectedResult: List<String>) =
        MonkeyMap(filename).data.shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestStartMap")
    fun testStartMap(filename: String, expectedResult: Array<Array<Field>>) =
        Map(MonkeyMap(filename).data).map.shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestStartingField")
    fun testStartingField(filename: String, expectedField: Position, expectedDirection: Field) {
        val map = Map(MonkeyMap(filename).data)

        assertAll(
            { map.currentPosition.shouldBe(expectedField) },
            { map.map[expectedField.y][expectedField.x].shouldBe(expectedDirection) }
        )
    }

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 6032)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of(55267)
            )

        @JvmStatic
        private fun getDataForTestReadMapData(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    listOf(
                        "        ...#",
                        "        .#..",
                        "        #...",
                        "        ....",
                        "...#.......#",
                        "........#...",
                        "..#....#....",
                        "..........#.",
                        "        ...#....",
                        "        .....#..",
                        "        .#......",
                        "        ......#."
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestReadOrders(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    listOf(
                        "10",
                        "R",
                        "5",
                        "L",
                        "5",
                        "R",
                        "10",
                        "L",
                        "4",
                        "R",
                        "5",
                        "L",
                        "5"
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestStartMap(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    arrayOf(
                        "                  ".toMapRow(),
                        "         >..#     ".toMapRow(),
                        "         .#..     ".toMapRow(),
                        "         #...     ".toMapRow(),
                        "         ....     ".toMapRow(),
                        " ...#.......#     ".toMapRow(),
                        " ........#...     ".toMapRow(),
                        " ..#....#....     ".toMapRow(),
                        " ..........#.     ".toMapRow(),
                        "         ...#.... ".toMapRow(),
                        "         .....#.. ".toMapRow(),
                        "         .#...... ".toMapRow(),
                        "         ......#. ".toMapRow(),
                        "                  ".toMapRow()
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestStartingField(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    Position(9, 1),
                    Field.RIGHT
                )
            )
    }
}

private fun Field.shouldBe(expectation: Field) = Assertions.assertEquals(expectation, this)
private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun Position.shouldBe(expectation: Position) = Assertions.assertEquals(expectation, this)
private fun Array<Array<Field>>.shouldBe(expectation: Array<Array<Field>>) =
    assertAll(
        { this.size.shouldBe(expectation.size) },
        {
            for (rowIndex in this.indices) {
                this[rowIndex].size.shouldBe(expectation[rowIndex].size)
            }
        },
        {
            for (rowIndex in this.indices) {
                for (colIndex in this[rowIndex].indices) {
                    this[rowIndex][colIndex].shouldBe(expectation[rowIndex][colIndex])
                }
            }
        }
    )
private fun Collection<*>.shouldBe(expectation: Collection<*>) = Assertions.assertEquals(expectation, this)

private fun String.toMapRow(): Array<Field> {
    val data = emptyList<Field>().toMutableList()

    for (index in this.indices) {
        when (this[index]) {
            '.' -> data += Field.FREE
            '#' -> data += Field.BLOCKED
            '^' -> data += Field.UP
            'v' -> data += Field.DOWN
            '<' -> data += Field.LEFT
            '>' -> data += Field.RIGHT
            else -> data += Field.UNAVAILABLE
        }
    }

    return data.toTypedArray()
}
