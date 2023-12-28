package de.havox_design.aoc2022.day17

import Day17._
import org.scalatest.funsuite.AnyFunSuite

class Day17ScalaTest extends AnyFunSuite {
  test("Day 17 - Part 2") {
    println("Solution for part2: " + solvePart2("input.txt"))
  }

  test("Day 17 - Part 2 - Sample") {
    assert(solvePart2("sample.txt") == 1514285714288L)
  }
}
