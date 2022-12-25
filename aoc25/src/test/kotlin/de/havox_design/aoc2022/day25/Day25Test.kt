package de.havox_design.aoc2022.day25

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

import de.havox_design.aoc2022.day25.SNAFUNumber.*
import org.junit.jupiter.api.assertAll

class Day25Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResultLong: Long, expectedResultSnafu: List<SNAFUNumber>) =
        assertAll(
            { FullOfHotAir(filename).processPart1().shouldBe(expectedResultLong) },
            { SNAFUNumber.toSnafu(FullOfHotAir(filename).processPart1()).shouldBe(expectedResultSnafu) }
        )

    @ParameterizedTest
    @MethodSource("getDataForTestConvertStringToSnafu")
    fun testConvertStringToSnafu(input: String, expectedResult: List<SNAFUNumber>) =
        SNAFUNumber.toSnafu(input).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestConvertToSnafu")
    fun testConvertToSnafu(input: Long, expectedResult: List<SNAFUNumber>) =
        SNAFUNumber.toSnafu(input).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestConvertToLong")
    fun testConvertToLong(input: List<SNAFUNumber>, expectedResult: Long) =
        SNAFUNumber.toLong(input).shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 4890, SNAFUNumber.toSnafu("2=-1=0"))
            )

        @JvmStatic
        private fun getDataForTestConvertStringToSnafu(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "1",
                    listOf(
                        ONE
                    )
                ),
                Arguments.of(
                    "2",
                    listOf(
                        TWO
                    )
                ),
                Arguments.of(
                    "1=",
                    listOf(
                        ONE,
                        DOUBLE_MINUS
                    )
                ),
                Arguments.of(
                    "1-",
                    listOf(
                        ONE,
                        MINUS
                    )
                ),
                Arguments.of(
                    "10",
                    listOf(
                        ONE,
                        ZERO
                    )
                ),
                Arguments.of(
                    "11",
                    listOf(
                        ONE,
                        ONE
                    )
                ),
                Arguments.of(
                    "12",
                    listOf(
                        ONE,
                        TWO
                    )
                ),
                Arguments.of(
                    "2=",
                    listOf(
                        TWO,
                        DOUBLE_MINUS
                    )
                ),
                Arguments.of(
                    "2-",
                    listOf(
                        TWO,
                        MINUS
                    )
                ),
                Arguments.of(
                    "20",
                    listOf(
                        TWO,
                        ZERO
                    )
                ),
                Arguments.of(
                    "1=0",
                    listOf(
                        ONE,
                        DOUBLE_MINUS,
                        ZERO
                    )
                ),
                Arguments.of(
                    "1-0",
                    listOf(
                        ONE,
                        MINUS,
                        ZERO
                    )
                ),
                Arguments.of(
                    "1=11-2",
                    listOf(
                        ONE,
                        DOUBLE_MINUS,
                        ONE,
                        ONE,
                        MINUS,
                        TWO
                    )
                ),
                Arguments.of(
                    "1-0---0",
                    listOf(
                        ONE,
                        MINUS,
                        ZERO,
                        MINUS,
                        MINUS,
                        MINUS,
                        ZERO
                    )
                ),
                Arguments.of(
                    "1121-1110-1=0",
                    listOf(
                        ONE,
                        ONE,
                        TWO,
                        ONE,
                        MINUS,
                        ONE,
                        ONE,
                        ONE,
                        ZERO,
                        MINUS,
                        ONE,
                        DOUBLE_MINUS,
                        ZERO
                    )
                ),
                Arguments.of(
                    "1=-0-2",
                    listOf(
                        ONE,
                        DOUBLE_MINUS,
                        MINUS,
                        ZERO,
                        MINUS,
                        TWO
                    )
                ),
                Arguments.of(
                    "12111",
                    listOf(
                        ONE,
                        TWO,
                        ONE,
                        ONE,
                        ONE
                    )
                ),
                Arguments.of(
                    "2=0=",
                    listOf(
                        TWO,
                        DOUBLE_MINUS,
                        ZERO,
                        DOUBLE_MINUS
                    )
                ),
                Arguments.of(
                    "1=-1=",
                    listOf(
                        ONE,
                        DOUBLE_MINUS,
                        MINUS,
                        ONE,
                        DOUBLE_MINUS
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestConvertToSnafu(): Stream<Arguments> =
            Stream.of(
                Arguments.of(0, SNAFUNumber.toSnafu("0")),
                Arguments.of(1, SNAFUNumber.toSnafu("1")),
                Arguments.of(2, SNAFUNumber.toSnafu("2")),
                Arguments.of(3, SNAFUNumber.toSnafu("1=")),
                Arguments.of(4, SNAFUNumber.toSnafu("1-")),
                Arguments.of(5, SNAFUNumber.toSnafu("10")),
                Arguments.of(6, SNAFUNumber.toSnafu("11")),
                Arguments.of(7, SNAFUNumber.toSnafu("12")),
                Arguments.of(8, SNAFUNumber.toSnafu("2=")),
                Arguments.of(9, SNAFUNumber.toSnafu("2-")),
                Arguments.of(10, SNAFUNumber.toSnafu("20")),
                Arguments.of(15, SNAFUNumber.toSnafu("1=0")),
                Arguments.of(20, SNAFUNumber.toSnafu("1-0")),
                Arguments.of(2022, SNAFUNumber.toSnafu("1=11-2")),
                Arguments.of(12345, SNAFUNumber.toSnafu("1-0---0")),
                Arguments.of(314159265, SNAFUNumber.toSnafu("1121-1110-1=0"))
            )

        @JvmStatic
        private fun getDataForTestConvertToLong(): Stream<Arguments> =
            Stream.of(
                Arguments.of(SNAFUNumber.toSnafu("1=-0-2"), 1747),
                Arguments.of(SNAFUNumber.toSnafu("12111"), 906),
                Arguments.of(SNAFUNumber.toSnafu("2=0="), 198),
                Arguments.of(SNAFUNumber.toSnafu("21"), 11),
                Arguments.of(SNAFUNumber.toSnafu("2=01"), 201),
                Arguments.of(SNAFUNumber.toSnafu("111"), 31),
                Arguments.of(SNAFUNumber.toSnafu("20012"), 1257),
                Arguments.of(SNAFUNumber.toSnafu("112"), 32),
                Arguments.of(SNAFUNumber.toSnafu("1=-1="), 353),
                Arguments.of(SNAFUNumber.toSnafu("1-12"), 107),
                Arguments.of(SNAFUNumber.toSnafu("12"), 7),
                Arguments.of(SNAFUNumber.toSnafu("1="), 3),
                Arguments.of(SNAFUNumber.toSnafu("122"), 37)
            )
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun Long.shouldBe(expectation: Long) = Assertions.assertEquals(expectation, this)
private fun String.shouldBe(expectation: String) = Assertions.assertEquals(expectation, this)
private fun List<SNAFUNumber>.shouldBe(expectation: List<SNAFUNumber>) = Assertions.assertEquals(expectation, this)
