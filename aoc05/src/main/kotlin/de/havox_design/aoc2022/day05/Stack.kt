package de.havox_design.aoc2022.day05

import java.util.LinkedList

data class Stack(val id: Int, var stack: LinkedList<Crate>) {
    companion object {
        fun emptyStackWithId(id: Int): Stack = Stack(id, LinkedList())
    }
}
