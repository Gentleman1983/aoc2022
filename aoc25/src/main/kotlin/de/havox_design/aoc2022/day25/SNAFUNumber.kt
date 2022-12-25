package de.havox_design.aoc2022.day25

import kotlin.math.*

enum class SNAFUNumber(private var symbol: Char, private var value: Long) {
    TWO('2', 2L),
    ONE('1', 1L),
    ZERO('0', 0L),
    MINUS('-', -1L),
    DOUBLE_MINUS('=', -2L);

    fun toSnafuSymbol(): Char = symbol
    fun toLong(): Long = value

    fun getIncrement(): SNAFUNumber =
        when (this) {
            TWO -> DOUBLE_MINUS
            ONE -> TWO
            ZERO -> ONE
            MINUS -> ZERO
            DOUBLE_MINUS -> MINUS
        }


    fun getDecrement(): SNAFUNumber =
        when (this) {
            TWO -> ONE
            ONE -> ZERO
            ZERO -> MINUS
            MINUS -> DOUBLE_MINUS
            DOUBLE_MINUS -> TWO
        }

    companion object {
        fun toLong(number: List<SNAFUNumber>): Long =
            number
                .reversed()
                .mapIndexed { i, value -> 5L.pow(i) * value.toLong() }
                .sum()

        fun toSnafu(number: String): List<SNAFUNumber> {
            val snafuNumber = emptyList<SNAFUNumber>().toMutableList()

            for (letter in number) {
                when (letter) {
                    DOUBLE_MINUS.toSnafuSymbol() -> snafuNumber += DOUBLE_MINUS
                    MINUS.toSnafuSymbol() -> snafuNumber += MINUS
                    ZERO.toSnafuSymbol() -> snafuNumber += ZERO
                    ONE.toSnafuSymbol() -> snafuNumber += ONE
                    TWO.toSnafuSymbol() -> snafuNumber += TWO
                }
            }

            return snafuNumber
        }

        fun toSnafu(number: Long): List<SNAFUNumber> {
            if (number == 0L) {
                return listOf(ZERO)
            }

            val snafuNumber = emptyList<SNAFUNumber>().toMutableList()
            var workingNumber = number

            while (workingNumber != 0L) {
                when ("012=-"[workingNumber.mod(5)]) {
                    DOUBLE_MINUS.toSnafuSymbol() -> snafuNumber += DOUBLE_MINUS
                    MINUS.toSnafuSymbol() -> snafuNumber += MINUS
                    ZERO.toSnafuSymbol() -> snafuNumber += ZERO
                    ONE.toSnafuSymbol() -> snafuNumber += ONE
                    TWO.toSnafuSymbol() -> snafuNumber += TWO
                }
                workingNumber = (workingNumber + 2).floorDiv(5)
            }

            return snafuNumber.reversed()
        }
    }
}

private fun Long.pow(e: Int): Long = this.toDouble().pow(e.toDouble()).toLong()