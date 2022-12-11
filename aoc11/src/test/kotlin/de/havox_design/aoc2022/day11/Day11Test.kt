package de.havox_design.aoc2022.day11

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day11Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestReadData")
    fun testReadData(filename: String, monkeys: List<Monkey>) =
        MonkeyInTheMiddle(filename).monkeys.shouldContainAll(monkeys)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1ItemMovement")
    fun testProcessPart1ItemMovement(filename: String, numberOfRounds: Int, expectedItems: Map<Int, List<Item>>) {
        MonkeyInTheMiddle(filename).processPart1(numberOfRounds)

        for (monkeyId in expectedItems.keys) {
            val monkey = Monkey.getMonkeyForId(monkeyId)!!
            monkey.startingItems.shouldContainAll(
                expectedItems[monkeyId]!!,
                "expected monkey $monkeyId to hold all items ${expectedItems[monkeyId]} but was ${monkey.startingItems} after $numberOfRounds rounds."
            )
        }

    }

    @Test
    fun verifyEqualsContractOnGridClass() {
        EqualsVerifier.forClass(Monkey.javaClass).suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT).verify()
    }

    companion object {
        @JvmStatic
        private fun getDataForTestReadData(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample1.txt",
                    listOf(
                        createMonkey(
                            0,
                            toItemList(79, 98),
                            { old: Int -> old * 19 },
                            23,
                            2,
                            3
                        ),
                        createMonkey(
                            1,
                            toItemList(54, 65, 75, 74),
                            { old: Int -> old + 6 },
                            19,
                            2,
                            0
                        ),
                        createMonkey(
                            2,
                            toItemList(79, 60, 97),
                            { old: Int -> old * old },
                            13,
                            1,
                            3
                        ),
                        createMonkey(
                            3,
                            toItemList(74),
                            { old: Int -> old + 3 },
                            17,
                            0,
                            1
                        )
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestProcessPart1ItemMovement(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample1.txt",
                    1,
                    mapOf(
                        Pair(0, toItemList(20, 23, 27, 26)),
                        Pair(1, toItemList(2080, 25, 167, 207, 401, 1046)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    2,
                    mapOf(
                        Pair(0, toItemList(695, 10, 71, 135, 350)),
                        Pair(1, toItemList(43, 49, 58, 55, 362)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    3,
                    mapOf(
                        Pair(0, toItemList(16, 18, 21, 20, 122)),
                        Pair(1, toItemList(1468, 22, 150, 286, 739)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    4,
                    mapOf(
                        Pair(0, toItemList(491, 9, 52, 97, 248, 34)),
                        Pair(1, toItemList(39, 45, 43, 258)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    5,
                    mapOf(
                        Pair(0, toItemList(15, 17, 16, 88, 1037)),
                        Pair(1, toItemList(20, 110, 205, 524, 72)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    6,
                    mapOf(
                        Pair(0, toItemList(8, 70, 176, 26, 34)),
                        Pair(1, toItemList(481, 32, 36, 186, 2190)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    7,
                    mapOf(
                        Pair(0, toItemList(162, 12, 14, 64, 732, 17)),
                        Pair(1, toItemList(148, 372, 55, 72)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    8,
                    mapOf(
                        Pair(0, toItemList(51, 126, 20, 26, 136)),
                        Pair(1, toItemList(343, 26, 30, 1546, 36)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    9,
                    mapOf(
                        Pair(0, toItemList(116, 10, 12, 517, 14)),
                        Pair(1, toItemList(108, 267, 43, 55, 288)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    10,
                    mapOf(
                        Pair(0, toItemList(91, 16, 20, 98)),
                        Pair(1, toItemList(481, 245, 22, 26, 1092, 30)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    15,
                    mapOf(
                        Pair(0, toItemList(83, 44, 8, 184, 9, 20, 26, 102)),
                        Pair(1, toItemList(110, 36)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    20,
                    mapOf(
                        Pair(0, toItemList(10, 12, 14, 26, 34)),
                        Pair(1, toItemList(245, 93, 53, 199, 115)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                )
            )

        private fun toItemList(vararg items: Int): List<Item> =
            toItemList(items.asList())

        private fun toItemList(startingItems: List<Int> = emptyList()): List<Item> {
            val itemList = emptyList<Item>().toMutableList()

            for (item in startingItems) {
                itemList += Item(item)
            }

            return itemList
        }

        private fun createMonkey(
            id: Int,
            startingItems: List<Item> = emptyList(),
            operation: (Int) -> Int,
            test: Int,
            trueMonkey: Int,
            falseMonkey: Int
        ): Monkey {
            val monkey = Monkey(id, startingItems)
            monkey.operation = operation
            monkey.addTestParameter(test, trueMonkey, falseMonkey)
            return monkey
        }
    }
}

private fun Collection<*>?.shouldBe(expectation: Collection<*>?) = Assertions.assertEquals(expectation, this)
private fun Collection<*>.shouldContainAll(expectation: Collection<*>) =
    Assertions.assertTrue(this.containsAll(expectation))

private fun Collection<*>.shouldContainAll(expectation: Collection<*>, message: String) =
    Assertions.assertTrue(this.containsAll(expectation), message)
