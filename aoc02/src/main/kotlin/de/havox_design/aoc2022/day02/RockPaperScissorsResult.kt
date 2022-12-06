package de.havox_design.aoc2022.day02

import java.util.*

enum class RockPaperScissorsResult(val symbol: String, val score: Int) {
    WIN("Z", 6),
    DRAW("Y", 3),
    LOSS("X", 0),
    UNDEFINED("UNDEFINED", -666);

    companion object {
        fun getValueBySymbol(s: String): RockPaperScissorsResult {
            for (index in RockPaperScissorsResult.values().indices) {
                val currentFigure: RockPaperScissorsResult = RockPaperScissorsResult.values()[index]

                if (currentFigure.symbol == s.uppercase(Locale.getDefault())) {
                    return currentFigure
                }
            }

            return UNDEFINED
        }
    }
}
