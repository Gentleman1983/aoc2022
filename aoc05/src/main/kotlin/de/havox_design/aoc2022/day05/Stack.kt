package de.havox_design.aoc2022.day05

data class Stack(val id: Int, var stack: List<Crate>) {
    companion object {
        fun emptyStackWithId(id: Int): Stack = Stack(id, emptyList())
    }
}
