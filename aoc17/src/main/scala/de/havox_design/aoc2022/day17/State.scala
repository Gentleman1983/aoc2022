package de.havox_design.aoc2022.day17

val shapes = Seq(
  Set(Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0)),
  Set(Point(1, 0), Point(0, 1), Point(1, 1), Point(2, 1), Point(1, 2)),
  Set(Point(0, 0), Point(1, 0), Point(2, 0), Point(2, 1), Point(2, 2)),
  Set(Point(0, 0), Point(0, 1), Point(0, 2), Point(0, 3)),
  Set(Point(0, 0), Point(1, 0), Point(0, 1), Point(1, 1)))

case class State(jets: String, grid: Set[Point], shapeIndex: Int, jetIndex: Int, height: Int):
  def step: State =
    val initialShape = shapes(shapeIndex % shapes.size)
      .move(3, height + 4)
    val (nextShape, nextJetIndex) = fall(initialShape, jetIndex)
    val nextHeight = height
      .max(nextShape.map(_.y).max)
    
    State(jets, grid ++ nextShape, shapeIndex + 1, nextJetIndex, nextHeight)

  private def fall(shape: Set[Point], jetIndex: Int): (Set[Point], Int) =
    val jet = jets(jetIndex % jets.length)
    val first = if jet != '>' then
      shape.move(-1, 0)
    else
      shape.move(1, 0)
    val second = if !first.canMove(grid) then
      shape
    else
      first
    val third = second
      .move(0, -1)
    
    if !third.canMove(grid) then
      (second, jetIndex + 1)
    else
      fall(third, jetIndex + 1)
end State
