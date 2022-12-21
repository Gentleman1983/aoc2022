package de.havox_design.aoc2022.day21

import de.havox_design.aoc2022.day21.Operator.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day21Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        MonkeyMath(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        MonkeyMath(filename).processPart2().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestReadData")
    fun testReadData(filename: String, expectedResult: Collection<Monkey>) =
        MonkeyMath(filename).data.shouldContainAll(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestMonkeysHaveRiddle")
    fun testMonkeysHaveRiddle(filename: String, expectedHaveRiddleToSolve: Boolean, monkeys: Collection<Monkey>) {
        MonkeyMath(filename).data.forEach { monkey ->
            if (monkeys.contains(monkey)) {
                monkey.needsToSolveRiddle().shouldBe(expectedHaveRiddleToSolve)
            } else {
                monkey.needsToSolveRiddle().shouldBe(!expectedHaveRiddleToSolve)
            }
        }
    }

    @ParameterizedTest
    @MethodSource("getDataForTestCalculateMonkeyValues")
    fun testCalculateMonkeyValues(filename: String, monkeyName: String, expectedResult: Int) {
        val data = MonkeyMath(filename).data
        val objectUnderTest = data.first { monkey -> monkey.name == monkeyName }
        objectUnderTest.calculateValue(data).shouldBe(expectedResult)
    }


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
        private fun getDataForTestReadData(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    listOf(
                        Monkey("root", Riddle("pppw", PLUS, "sjmn")),
                        Monkey("dbpl", number = 5),
                        Monkey("cczh", Riddle("sllz", PLUS, "lgvd")),
                        Monkey("zczc", number = 2),
                        Monkey("ptdq", Riddle("humn", MINUS, "dvpt")),
                        Monkey("dvpt", number = 3),
                        Monkey("lfqf", number = 4),
                        Monkey("humn", number = 5),
                        Monkey("ljgn", number = 2),
                        Monkey("sjmn", Riddle("drzm", MULTIPLY, "dbpl")),
                        Monkey("sllz", number = 4),
                        Monkey("pppw", Riddle("cczh", DIVIDE, "lfqf")),
                        Monkey("lgvd", Riddle("ljgn", MULTIPLY, "ptdq")),
                        Monkey("drzm", Riddle("hmdt", MINUS, "zczc")),
                        Monkey("hmdt", number = 32)
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestMonkeysHaveRiddle(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    true,
                    listOf(
                        Monkey("root", Riddle("pppw", PLUS, "sjmn")),
                        Monkey("cczh", Riddle("sllz", PLUS, "lgvd")),
                        Monkey("ptdq", Riddle("humn", MINUS, "dvpt")),
                        Monkey("sjmn", Riddle("drzm", MULTIPLY, "dbpl")),
                        Monkey("pppw", Riddle("cczh", DIVIDE, "lfqf")),
                        Monkey("lgvd", Riddle("ljgn", MULTIPLY, "ptdq")),
                        Monkey("drzm", Riddle("hmdt", MINUS, "zczc"))
                    )
                ),
                Arguments.of(
                    "sample.txt",
                    false,
                    listOf(
                        Monkey("dbpl", number = 5),
                        Monkey("zczc", number = 2),
                        Monkey("dvpt", number = 3),
                        Monkey("lfqf", number = 4),
                        Monkey("humn", number = 5),
                        Monkey("ljgn", number = 2),
                        Monkey("sllz", number = 4),
                        Monkey("hmdt", number = 32)
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestCalculateMonkeyValues(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", "root", 152),
                Arguments.of("sample.txt", "dbpl", 5),
                Arguments.of("sample.txt", "cczh", 8),
                Arguments.of("sample.txt", "zczc", 2),
                Arguments.of("sample.txt", "ptdq", 2),
                Arguments.of("sample.txt", "dvpt", 3),
                Arguments.of("sample.txt", "lfqf", 4),
                Arguments.of("sample.txt", "humn", 5),
                Arguments.of("sample.txt", "ljgn", 2),
                Arguments.of("sample.txt", "sjmn", 150),
                Arguments.of("sample.txt", "sllz", 4),
                Arguments.of("sample.txt", "pppw", 2),
                Arguments.of("sample.txt", "lgvd", 4),
                Arguments.of("sample.txt", "drzm", 30),
                Arguments.of("sample.txt", "hmdt", 32)
            )
    }
}

private fun Boolean.shouldBe(expectation: Boolean) = Assertions.assertEquals(expectation, this)
private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun Collection<*>.shouldContainAll(expectation: Collection<*>) =
    Assertions.assertTrue(this.containsAll(expectation))