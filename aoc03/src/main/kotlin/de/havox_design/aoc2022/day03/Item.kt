package de.havox_design.aoc2022.day03

data class Item(val symbol: String) {
    fun getScoreForItem(): Int =
        ItemValue.getScoreByDefault(symbol)
}
