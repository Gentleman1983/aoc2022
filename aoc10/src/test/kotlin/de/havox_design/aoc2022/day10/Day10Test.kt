package de.havox_design.aoc2022.day10

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day10Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestFindOrderByOrderName")
    fun testFindOrderByOrderName(orderName: String, expectedOrder: Order) =
        Order.findByOrderName(orderName).shouldBe(expectedOrder)

    @ParameterizedTest
    @MethodSource("getDataForReadFile")
    fun testReadFile(filename: String, expectedInstructions: List<Instruction>) {
        val objectUnderTest = CathodeRayTube(filename)

        assertAll(
            { objectUnderTest.instructions.shouldContainAll(expectedInstructions) },
            { objectUnderTest.instructions.shouldBe(expectedInstructions) }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForProcessPart1")
    fun testProcessPart1(filename: String, expectedSignalStrength: Int) =
        CathodeRayTube(filename).processPart1().shouldBe(expectedSignalStrength)

    @ParameterizedTest
    @MethodSource("getDataForProcessPart2")
    fun testProcessPart2(filename: String, expectedOutput: String) =
        CathodeRayTube(filename).processPart2().shouldBe(expectedOutput)

    companion object {
        @JvmStatic
        private fun getDataForTestFindOrderByOrderName(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Order.NOOP.orderName, Order.NOOP),
                Arguments.of(Order.ADDX.orderName, Order.ADDX),
                Arguments.of(Order.UNKNOWN.orderName, Order.UNKNOWN),
                Arguments.of("foo", Order.UNKNOWN),
                Arguments.of("bar", Order.UNKNOWN)
            )

        @JvmStatic
        private fun getDataForReadFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample1.txt",
                    listOf(
                        Instruction(Order.NOOP),
                        Instruction(Order.ADDX, 3),
                        Instruction(Order.ADDX, -5)
                    )
                )
            )

        @JvmStatic
        private fun getDataForProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample1.txt", 0),
                Arguments.of("sample2.txt", 13140)
            )

        @JvmStatic
        private fun getDataForProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample2.txt",
                    "##..##..##..##..##..##..##..##..##..##..\n" +
                            "###...###...###...###...###...###...###.\n" +
                            "####....####....####....####....####....\n" +
                            "#####.....#####.....#####.....#####.....\n" +
                            "######......######......######......####\n" +
                            "#######.......#######.......#######....."
                )
            )
    }
}

private fun Int.shouldBe(expectation: Int) = org.junit.jupiter.api.Assertions.assertEquals(expectation, this)
private fun Order?.shouldBe(expectation: Order?) = org.junit.jupiter.api.Assertions.assertEquals(expectation, this)
private fun String.shouldBe(expectation: String) = org.junit.jupiter.api.Assertions.assertEquals(expectation, this)
private fun Collection<*>?.shouldBe(expectation: Collection<*>?) = Assertions.assertEquals(expectation, this)
private fun Collection<*>.shouldContainAll(expectation: Collection<*>) =
    Assertions.assertTrue(this.containsAll(expectation))
