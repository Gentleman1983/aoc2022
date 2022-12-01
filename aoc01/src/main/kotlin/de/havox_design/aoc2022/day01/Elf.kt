package de.havox_design.aoc2022.day01

data class Elf(val items: List<Item>) {
    fun sumCalories(): Int =
        items.sumOf { item -> item.value }
}
