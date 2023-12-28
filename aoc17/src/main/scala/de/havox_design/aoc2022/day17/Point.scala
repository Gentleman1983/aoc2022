package de.havox_design.aoc2022.day17

case class Point(x: Int, y: Int)

extension (shape: Set[Point])
  def move(dx: Int, dy: Int): Set[Point] = shape.map(p => Point(p.x + dx, p.y + dy))
  def canMove(grid: Set[Point]): Boolean = shape.forall(p => p.x > 0 && p.x < 8 && !grid.contains(p))
