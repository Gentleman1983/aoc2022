package de.havox_design.aoc2022.day17

object Day17 {
  def solvePart2(filename: String, runs: Int = 10000): Long = {
    val guess = 1000
    val height = simulate(readData(filename).next())
      .slice(1, 5 * guess)
      .toSeq
    val delta = height.sliding(2).map(s => s.last - s.head).toSeq
    val end = delta.size - guess
    val start = delta.lastIndexOfSlice(delta.takeRight(guess), end - 1)
    val cycleHeight = height(end) - height(start)
    val cycleWidth = end - start
    val offset = 1000000000000L - 1 - start
    val quotient = offset / cycleWidth
    val remainder = offset % cycleWidth

    (quotient * cycleHeight) + height(start + remainder.toInt)
  }

  def main(args: Array[String]): Unit = {
    println("Solution for part2: " + solvePart2("input.txt"))
  }

  private def simulate(jets: String): Iterator[Int] =
    val initial = State(jets, Set.tabulate(8)(Point(_, 0)), 0, 0, 0)

    Iterator
      .iterate(initial)(_.step)
      .map(_.height)

  private def readData(filename: String): Iterator[String] =
    scala
      .io
      .Source
      .fromResource(filename)
      .getLines()
}
