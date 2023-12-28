package de.havox_design.aoc2022.day11

import Day11._
import org.scalatest.funsuite.AnyFunSuite

class Day11ScalaTest extends AnyFunSuite {
  test("Day 11 - Part 2") {
    println("Solution for part2: " + solvePart2("input.txt"))
  }

  test("Day 11 - Part 2 - Sample - 1 Run") {
    assert(solvePart2("sample1.txt", 1) == 24L)
  }

  test("Day 11 - Part 2 - Sample - 20 Runs") {
    assert(solvePart2("sample1.txt", 20) == 99L * 103L)
  }

  test("Day 11 - Part 2 - Sample - 1000 Runs") {
    assert(solvePart2("sample1.txt", 1000) == 5204L * 5192L)
  }

  test("Day 11 - Part 2 - Sample - 2000 Runs") {
    assert(solvePart2("sample1.txt", 2000) == 10419L * 10391L)
  }

  test("Day 11 - Part 2 - Sample - 3000 Runs") {
    assert(solvePart2("sample1.txt", 3000) == 15638L * 15593L)
  }

  test("Day 11 - Part 2 - Sample - 4000 Runs") {
    assert(solvePart2("sample1.txt", 4000) == 20858L * 20797L)
  }

  test("Day 11 - Part 2 - Sample - 5000 Runs") {
    assert(solvePart2("sample1.txt", 5000) == 26075L * 26000L)
  }

  test("Day 11 - Part 2 - Sample - 6000 Runs") {
    assert(solvePart2("sample1.txt", 6000) == 31294L * 31204L)
  }

  test("Day 11 - Part 2 - Sample - 7000 Runs") {
    assert(solvePart2("sample1.txt", 7000) == 36508L * 36400L)
  }

  test("Day 11 - Part 2 - Sample - 8000 Runs") {
    assert(solvePart2("sample1.txt", 8000) == 41728L * 41606L)
  }

  test("Day 11 - Part 2 - Sample - 9000 Runs") {
    assert(solvePart2("sample1.txt", 9000) == 46945L * 46807L)
  }

  test("Day 11 - Part 2 - Sample - 10000 Runs") {
    assert(solvePart2("sample1.txt", 10000) == 52166L * 52013L)
  }
}
