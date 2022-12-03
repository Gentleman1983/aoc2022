package de.havox_design.aoc2022.day03

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day03Test {
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
    fun testGetItemValueScores(symbol: String, expectedScore: Int) {
        ItemValue
            .getScoreByDefault(symbol)
            .shouldBe(expectedScore)
    }

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
    fun testGetItemValueBySymbol(symbol: String, expectedItemValue: ItemValue) {
        ItemValue
            .getValueBySymbol(symbol)
            .shouldBe(expectedItemValue)
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
        "Z,52",
        "foo, 0"
    )
    fun testGetScoresByItem(symbol: String, expectedScore: Int) {
        Item(symbol)
            .getScoreForItem()
            .shouldBe(expectedScore)
    }

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
        var expectedDuplicateItem: Item = Item(expectedDuplicateSymbol)

        var duplicates: Set<Item> = Knapsack
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
    fun testGetScoresByContent(knapsackContent: String, expectedScore: Int) {
        Knapsack
            .getKnapsackForString(knapsackContent)
            .calculateScoreOfDuplicateItems()
            .shouldBe(expectedScore)
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun ItemValue.shouldBe(expectation: ItemValue) = Assertions.assertEquals(expectation, this)