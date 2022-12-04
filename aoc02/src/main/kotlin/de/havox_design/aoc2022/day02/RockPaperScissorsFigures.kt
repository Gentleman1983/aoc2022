package de.havox_design.aoc2022.day02

import java.util.*

enum class RockPaperScissorsFigures(val symbol: String, val counterSymbol: String, val score: Int) {
    ROCK("A", "X", 1),
    PAPER("B", "Y", 2),
    SCISSORS("C", "Z", 3),
    UNDEFINED("UNDEFINED", "UNDEFINED", 0);

    companion object {
        fun getValueBySymbol(s: String): RockPaperScissorsFigures {
            for (index in RockPaperScissorsFigures.values().indices) {
                val currentFigure: RockPaperScissorsFigures = RockPaperScissorsFigures.values()[index]

                if (currentFigure.symbol == s.uppercase(Locale.getDefault()) || currentFigure.counterSymbol == s.uppercase(
                        Locale.getDefault()
                    )
                ) {
                    return currentFigure
                }
            }

            return UNDEFINED
        }
    }
}
