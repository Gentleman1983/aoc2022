package de.havox_design.aoc2022.day16

object Day16 {
  def solvePart2(filename: String, runs: Int = 10000): Long = {
    val sets = explore(readData(filename).toIndexedSeq, 26)
    val disjoint = for
      (you, score1) <- sets
      (elephant, score2) <- sets
      if you.intersect(elephant).isEmpty
    yield score1 + score2
    disjoint.max
  }

  def main(args: Array[String]): Unit = {
    println("Solution for part2: " + solvePart2("input.txt"))
  }

  private def explore(input: Seq[String], initial: Int): Map[Set[String], Int] =
    val (valves, distance, todo) = parseInput(input)
    val score = collection
      .mutable
      .Map[Set[String], Int]()
      .withDefaultValue(0)

    def step(todo: Set[String], done: Set[String], from: String, time: Int, pressure: Int): Unit =
      score(done) = score(done)
        .max(pressure)

      for
        next <- todo
        remaining = time - distance(from)(next)
        if remaining > 0
        extra = remaining * valves(next).flow
      do step(todo - next, done + next, next, remaining, pressure + extra)
    end step

    step(todo, Set(), "AA", initial, 0)
    score
      .toMap
  end explore

  private def bfs(graph: Map[String, ScalaValve], start: String): Map[String, Int] =
    val todo = collection
      .mutable
      .Queue(start)
    val cost = collection
      .mutable
      .Map(start -> 1)

    while todo.nonEmpty do {
      val current = todo
        .dequeue()
      graph(current)
        .neighbours
        .filterNot(cost.contains)
        .foreach { next =>
          todo
            .enqueue(next)

          cost(next) = cost(current) + 1
        }
    }

    cost.toMap

  private def parseInput(input: Seq[String]): (Map[String, ScalaValve], Map[String, Map[String, Int]], Set[String]) =
    val valves = input
      .map { line =>
        val Array(_, name, flow, edges: _*) = line
          .split("[^A-Z0-9]+"): @unchecked
        name -> ScalaValve(flow.toInt, edges)
      }
      .toMap
    val distance = valves
      .map((k, v) => k -> bfs(valves, k))
    val todo = valves
      .filter((k, v) => v.flow > 0)
      .keySet

    (valves, distance, todo)

  private def readData(filename: String): Iterator[String] =
    scala
      .io
      .Source
      .fromResource(filename)
      .getLines()
}
