package de.havox_design.aoc2022.day17

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day17Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        PyroclasticFlow(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        PyroclasticFlow(filename).processPart2().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestRocks")
    fun testRocks(rock: Rock, expectedWidth: Int, expectedHeigth: Int, expectedBlockedElements: Set<Position>) =
        assertAll(
            { rock.dimensionX.shouldBe(expectedWidth) },
            { rock.dimensionY.shouldBe(expectedHeigth) },
            { rock.getBlockedPositions().shouldBe(expectedBlockedElements) }
        )

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 0)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 0)
            )

        @JvmStatic
        private fun getDataForTestRocks(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Rock.ARROW, 3, 3, getBlockedFieldsForString("..#\n..#\n###")),
                Arguments.of(Rock.BOX, 2, 2, getBlockedFieldsForString("##\n##")),
                Arguments.of(Rock.HORIZONTAL_LINE, 4, 1, getBlockedFieldsForString("####")),
                Arguments.of(Rock.PLUS, 3, 3, getBlockedFieldsForString(".#.\n###\n.#.")),
                Arguments.of(Rock.VERTICAL_LINE, 1, 4, getBlockedFieldsForString("#\n#\n#\n#"))
            )

        private fun getBlockedFieldsForString(data: String): Set<Position> {
            val result: MutableSet<Position> = emptySet<Position>().toMutableSet()

            val rows = data.split("\n")

            for (rowIndex in rows.indices) {
                val row = rows[rowIndex]

                for (colIndex in row.indices) {
                    val letter = row[colIndex]

                    if (letter == '#') {
                        result += Position(colIndex, rowIndex)
                    }
                }
            }

            return result
        }
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun Set<*>.shouldBe(expectation: Set<*>) = Assertions.assertEquals(expectation, this)
