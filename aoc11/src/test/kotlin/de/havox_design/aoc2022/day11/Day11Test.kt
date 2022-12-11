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
