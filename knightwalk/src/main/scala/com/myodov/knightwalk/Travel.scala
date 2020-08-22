package com.myodov.knightwalk

import scala.collection.immutable.ListSet

/** Some particular travel of a knight over the chess board. */
case class Travel(visited: ListSet[Pos]) {

  /** Constructor like `Travel(Pos("A4")).` */
  def this(startPos: Pos) = this(ListSet(startPos))

  /** Constructor like `Travel(Pos("D4"), Pos("E6"), Pos("F4")).` */
  def this(visited: Pos*) = this(ListSet.from(visited))

  override def toString: String = "[" + visited.map(p => p.toString).mkString(" ") + "]"

  val currentPos: Pos = visited.last

  val visitedAllBoard: Boolean = (visited.size == 64)

  /** What can be next possible knight moves in this travel.
   *
   * Contains all the possible next knight moves, if they reach the squares not yet visited.
   * */
  val nextMoves: List[Pos] = currentPos.knightMoves.filterNot(move => visited.contains(move))

  /** What can be next travels with one more knight move.
   *
   * Contains all the possible correct subsequent knight travels with one more next knight move.
   * */
  lazy val nextTravels: List[Travel] = nextMoves.map(move => Travel(visited + move))

  /** From the current travel, try to find the solution to “knight travel” problem recursively.
   *
   * Return as soon as solution has been found.
   * */
  lazy val findKnightTravelSolution: Option[Travel] = {
    val checkpointSize = 5000000

    Travel.counter += 1
    val counter = Travel.counter
    if (counter % checkpointSize == 0) {
      val thisTS = System.nanoTime
      val durationSeconds = (thisTS - Travel.lastTS) / 1_000_000_000.0
      val tps = checkpointSize / durationSeconds

      println(f"$counter; $tps%.0f TPS")
      Travel.lastTS = thisTS
    }
    if (visitedAllBoard) {
      // Solution found in this branch of travels!
      Some(this)
    } else if (nextTravels.isEmpty) {
      // No solution in this branch of travels.
      None
    } else {
      // Generate the next travels and depth-search them.
      val nextSolutions: List[Option[Travel]] = nextTravels.map(tr => tr.findKnightTravelSolution)
      nextSolutions.find(_.nonEmpty).flatten
    }
  }
}

object Travel {
  /** Constructor like `Travel(Pos("A4")).` */
  final def apply(startPos: Pos) = new Travel(startPos)

  /** Constructor like `Travel(Pos("D4"), Pos("E6"), Pos("F4")).` */
  final def apply(visited: Pos*) = new Travel(visited: _*)

  var counter = 0l
  var lastTS = System.nanoTime
}
