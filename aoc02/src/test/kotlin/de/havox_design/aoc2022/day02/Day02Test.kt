package de.havox_design.aoc2022.day02

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day02Test {
    @ParameterizedTest
    @CsvSource(
        "rock_rock.txt, 4",
        "rock_paper.txt, 8",
        "rock_scissors.txt, 3",
        "paper_rock.txt, 1",
        "paper_paper.txt, 5",
        "paper_scissors.txt, 9",
        "scissors_rock.txt, 7",
        "scissors_paper.txt, 3",
        "scissors_scissors.txt, 6",
        "sample.txt, 15"
    )
    fun testPlaybooks(filename: String, expectedScore: Int) {
        RockPaperScissorsGame(filename)
            .getResultForGuide()
            .shouldBe(expectedScore)
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)