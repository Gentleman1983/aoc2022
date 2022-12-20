package de.havox_design.aoc2022.day20

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day20Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Long) =
        GrovePositioningSystem(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Long) =
        GrovePositioningSystem(filename).processPart2().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestReadData")
    fun testReadData(filename: String, expectedResult: List<Element>) =
        GrovePositioningSystem(filename).data.map { v -> v.value }.shouldBe(expectedResult.map { v -> v.value })

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 3L)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 1623178306L)
            )

        @JvmStatic
        private fun getDataForTestReadData(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    listOf(
                        Element(1),
                        Element(2),
                        Element(-3),
                        Element(3),
                        Element(-2),
                        Element(0),
                        Element(4)
                    )
                )
            )
    }
}

private fun Long.shouldBe(expectation: Long) = Assertions.assertEquals(expectation, this)
private fun List<*>.shouldBe(expectation: List<*>) = Assertions.assertEquals(expectation, this)
