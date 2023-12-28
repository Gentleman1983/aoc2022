package de.havox_design.aoc2022.day11

case class ScalaMonkey(items: Seq[Long], operation: Long => Long, test: Int, yes: Int, no: Int, count: Long):
  def compose(g: Long => Long): ScalaMonkey = 
    copy(operation = operation.andThen(g))
    
  def finish: ScalaMonkey = 
    copy(items = Seq(), count = count + items.size)
    
  def accept(extra: Seq[Long]): ScalaMonkey = 
    copy(items = items ++ extra)
