package de.havox_design.aoc2022.day03

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class Day03Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @CsvSource(
        "a,1",
        "b,2",
        "c,3",
        "d,4",
        "e,5",
        "f,6",
        "g,7",
        "h,8",
        "i,9",
        "j,10",
        "k,11",
        "l,12",
        "m,13",
        "n,14",
        "o,15",
        "p,16",
        "q,17",
        "r,18",
        "s,19",
        "t,20",
        "u,21",
        "v,22",
        "w,23",
        "x,24",
        "y,25",
        "z,26",
        "A,27",
        "B,28",
        "C,29",
        "D,30",
        "E,31",
        "F,32",
        "G,33",
        "H,34",
        "I,35",
        "J,36",
        "K,37",
        "L,38",
        "M,39",
        "N,40",
        "O,41",
        "P,42",
        "Q,43",
        "R,44",
        "S,45",
        "T,46",
        "U,47",
        "V,48",
        "W,49",
        "X,50",
        "Y,51",
        "Z,52"
    )
    fun testGetItemValueScores(symbol: String, expectedScore: Int) =
        ItemValue
            .getScoreByDefault(symbol)
            .shouldBe(expectedScore)

    @ParameterizedTest
    @CsvSource(
        "a,A",
        "b,B",
        "c,C",
        "d,D",
        "e,E",
        "f,F",
        "g,G",
        "h,H",
        "i,I",
        "j,J",
        "k,K",
        "l,L",
        "m,M",
        "n,N",
        "o,O",
        "p,P",
        "q,Q",
        "r,R",
        "s,S",
        "t,T",
        "u,U",
        "v,V",
        "w,W",
        "x,X",
        "y,Y",
        "z,Z",
        "A,A",
        "B,B",
        "C,C",
        "D,D",
        "E,E",
        "F,F",
        "G,G",
        "H,H",
        "I,I",
        "J,J",
        "K,K",
        "L,L",
        "M,M",
        "N,N",
        "O,O",
        "P,P",
        "Q,Q",
        "R,R",
        "S,S",
        "T,T",
        "U,U",
        "V,V",
        "W,W",
        "X,X",
        "Y,Y",
        "Z,Z"
    )
    fun testGetItemValueBySymbol(symbol: String, expectedItemValue: ItemValue) =
        ItemValue
            .getValueBySymbol(symbol)
            .shouldBe(expectedItemValue)

    @ParameterizedTest
    @CsvSource(
        "a,1",
        "b,2",
        "c,3",
        "d,4",
        "e,5",
        "f,6",
        "g,7",
        "h,8",
        "i,9",
        "j,10",
        "k,11",
        "l,12",
        "m,13",
        "n,14",
        "o,15",
        "p,16",
        "q,17",
        "r,18",
        "s,19",
        "t,20",
        "u,21",
        "v,22",
        "w,23",
        "x,24",
        "y,25",
        "z,26",
        "A,27",
        "B,28",
        "C,29",
        "D,30",
        "E,31",
        "F,32",
        "G,33",
        "H,34",
        "I,35",
        "J,36",
        "K,37",
        "L,38",
        "M,39",
        "N,40",
        "O,41",
        "P,42",
        "Q,43",
        "R,44",
        "S,45",
        "T,46",
        "U,47",
        "V,48",
        "W,49",
        "X,50",
        "Y,51",
        "Z,52",
        "foo, 0"
    )
    fun testGetScoresByItem(symbol: String, expectedScore: Int) =
        Item(symbol)
            .getScoreForItem()
            .shouldBe(expectedScore)

    @ParameterizedTest
    @CsvSource(
        "vJrwpWtwJgWrhcsFMMfFFhFp,p",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL,L",
        "PmmdzqPrVvPwwTWBwg,P",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn,v",
        "ttgJtRGJQctTZtZT,t",
        "CrZsJsPPZsGzwwsLwLmpwMDw,s"
    )
    fun testDuplicateByContent(knapsackContent: String, expectedDuplicateSymbol: String) {
        val expectedDuplicateItem = Item(expectedDuplicateSymbol)

        val duplicates: Set<Item> = Knapsack
            .getKnapsackForString(knapsackContent)
            .findDuplicateItems()

        Assertions.assertTrue(duplicates.size == 1)
        Assertions.assertTrue(duplicates.contains(expectedDuplicateItem))
    }

    @ParameterizedTest
    @CsvSource(
        "vJrwpWtwJgWrhcsFMMfFFhFp,16",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL,38",
        "PmmdzqPrVvPwwTWBwg,42",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn,22",
        "ttgJtRGJQctTZtZT,20",
        "CrZsJsPPZsGzwwsLwLmpwMDw,19"
    )
    fun testGetScoresByContent(knapsackContent: String, expectedScore: Int) =
        Knapsack
            .getKnapsackForString(knapsackContent)
            .calculateScoreOfDuplicateItems()
            .shouldBe(expectedScore)

    @ParameterizedTest
    @MethodSource("getDataForTestGetDuplicatesForFile")
    fun testGetDuplicatesForFile(filename: String, expectedDuplicates: List<Item>) =
        RucksackReorganization(filename)
            .getDuplicatesFromList()
            .shouldContainAll(expectedDuplicates)

    @ParameterizedTest
    @CsvSource(
        "sampleRow1.txt,16",
        "sampleRow2.txt,38",
        "sampleRow3.txt,42",
        "sampleRow4.txt,22",
        "sampleRow5.txt,20",
        "sampleRow6.txt,19",
        "sample.txt,157"
    )
    fun testGetScoresForFile(filename: String, expectedScore: Int) =
        RucksackReorganization(filename)
            .getDuplicatesScoreFromList()
            .shouldBe(expectedScore)

    @ParameterizedTest
    @ValueSource(
        strings = [
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw"
        ]

    )
    fun testDistinctItemsForKnapsack(data: String) {
        for (index in data.indices) {
            val currentLetter = Item(data.substring(index, index + 1))

            Assertions
                .assertTrue(
                    Knapsack
                        .getKnapsackForString(data)
                        .listDistinctItems()
                        .contains(currentLetter)
                )
        }
    }

    @ParameterizedTest
    @MethodSource("getDataForTestGetBadgesFromFile")
    fun testGetBadgesFromFile(filename: String, expectedBadges: List<Item>) {
        RucksackReorganization(filename)
            .detectBadgesFromList()
            .shouldContainAll(expectedBadges)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestGetBadgeScoreFromFile")
    fun testGetBadgeScoreFromFile(filename: String, expectedScore: Int) {
        RucksackReorganization(filename)
            .getBadgesScoreFromList()
            .shouldBe(expectedScore)
    }


    companion object {
        @JvmStatic
        private fun getDataForTestGetDuplicatesForFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sampleRow1.txt", listOf(Item("p"))),
                Arguments.of("sampleRow2.txt", listOf(Item("L"))),
                Arguments.of("sampleRow3.txt", listOf(Item("P"))),
                Arguments.of("sampleRow4.txt", listOf(Item("v"))),
                Arguments.of("sampleRow5.txt", listOf(Item("t"))),
                Arguments.of("sampleRow6.txt", listOf(Item("s"))),
                Arguments.of(
                    "sample.txt", listOf(
                        Item("p"),
                        Item("L"),
                        Item("P"),
                        Item("v"),
                        Item("t"),
                        Item("s")
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestGetBadgesFromFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of("badges1.txt", listOf(Item("r"))),
                Arguments.of("badges2.txt", listOf(Item("Z"))),
                Arguments.of(
                    "sample.txt", listOf(
                        Item("r"),
                        Item("Z")
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestGetBadgeScoreFromFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of("badges1.txt", 18),
                Arguments.of("badges2.txt", 52),
                Arguments.of("sample.txt", 70)
            )
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun ItemValue.shouldBe(expectation: ItemValue) = Assertions.assertEquals(expectation, this)
private fun List<Item>.shouldContainAll(expectation: Collection<Item>) =
    Assertions.assertTrue(this.containsAll(expectation))
