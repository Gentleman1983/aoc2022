package de.havox_design.aoc2022.day03

import java.util.*

enum class ItemValue(val lowerCaseValue: Int, val upperCaseValue: Int, val symbol: String) {
    A(1, 27, "A"),
    B(2, 28, "B"),
    C(3, 29, "C"),
    D(4, 30, "D"),
    E(5, 31, "E"),
    F(6, 32, "F"),
    G(7, 33, "G"),
    H(8, 34, "H"),
    I(9, 35, "I"),
    J(10, 36, "J"),
    K(11, 37, "K"),
    L(12, 38, "L"),
    M(13, 39, "M"),
    N(14, 40, "N"),
    O(15, 41, "O"),
    P(16, 42, "P"),
    Q(17, 43, "Q"),
    R(18, 44, "R"),
    S(19, 45, "S"),
    T(20, 46, "T"),
    U(21, 47, "U"),
    V(22, 48, "V"),
    W(23, 49, "W"),
    X(24, 50, "X"),
    Y(25, 51, "Y"),
    Z(26, 52, "Z"),
    UNDEFINED(0, 0, "UNDEFINED");

    companion object {
        fun getValueBySymbol(s: String): ItemValue {
            for (index in ItemValue.values().indices) {
                val currentFigure: ItemValue = ItemValue.values()[index]

                if (currentFigure.symbol == s.uppercase(Locale.getDefault())) {
                    return currentFigure
                }
            }

            return UNDEFINED
        }

        fun getScoreByDefault(s: String): Int {
            val v: ItemValue = getValueBySymbol(s)

            return if (s.isLowerCase()) {
                v.lowerCaseValue
            } else {
                v.upperCaseValue
            }
        }
    }
}

private fun String.isUpperCase(): Boolean =
    this.uppercase(Locale.getDefault()) == this

private fun String.isLowerCase(): Boolean =
    !this.isUpperCase()
