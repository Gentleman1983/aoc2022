package de.havox_design.aoc2022.day11


object Day11 {
  def solvePart2(filename: String, runs: Int = 10000): Long = {
    val monkeys = parseMonkeys(readData(filename).toIndexedSeq)
    val product = monkeys.map(_.test).product
    val adjusted = monkeys.map(_.compose(_ % product))
    play(adjusted, runs)
  }

  def main(args: Array[String]): Unit = {
    println("Solution for part2: " + solvePart2("input.txt"))
  }

  private def play(monkeys: Seq[ScalaMonkey], rounds: Int): Long =
    Iterator.iterate(monkeys)(step).drop(rounds).next().map(_.count).sorted.takeRight(2).product

  private def step(monkeys: Seq[ScalaMonkey]): Seq[ScalaMonkey] =
    monkeys.indices.foldLeft(monkeys) { (monkeys, index) =>
      val ScalaMonkey(items, operation, test, yes, no, _) = monkeys(index)
      val (pass, fail) = items.map(operation).partition(_ % test == 0)
      monkeys
        .updated(index, monkeys(index).finish)
        .updated(yes, monkeys(yes).accept(pass))
        .updated(no, monkeys(no).accept(fail))
    }

  private def parseNumbers(s: String): Seq[Int] = s.split("\\D+").tail.map(_.toInt).toSeq

  private def parseOperation(s: String): Long => Long = s.split(" ").takeRight(2) match
    case Array("*", "old") => x
      => x * x
    case Array("*", y) => x
      => x * y.toLong
    case Array("+", y) => x
      => x + y.toLong

  private def parseMonkeys(input: Seq[String]): Seq[ScalaMonkey] =
    input.grouped(7).toSeq.map { lines =>
      val items = parseNumbers(lines(1)).map(_.toLong)
      val operation = parseOperation(lines(2))
      val test = parseNumbers(lines(3)).head
      val yes = parseNumbers(lines(4)).head
      val no = parseNumbers(lines(5)).head
      ScalaMonkey(items, operation, test, yes, no, 0)
    }

  private def readData(filename: String): Iterator[String] =
    scala
      .io
      .Source
      .fromResource(filename)
      .getLines()
}
