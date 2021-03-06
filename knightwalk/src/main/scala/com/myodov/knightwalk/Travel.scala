package com.myodov.knightwalk

import scala.collection.immutable.ListSet

/** Some particular travel of a knight over the chess board. */
case class Travel(visited: ListSet[Pos]) {

  /** Constructor like `Travel(Pos("A4")).` */
  def this(startPos: Pos) = this(ListSet(startPos))

  /** Constructor like `Travel(Pos("D4"), Pos("E6"), Pos("F4")).` */
  def this(visited: Pos*) = this(ListSet.from(visited))

  override def toString: String = "[" + visited.map(p => p.toString).mkString(" ") + "]"
  //  override def toString: String = visited.zipWithIndex.map { case (p, i) => s"${i + 1}:$p" }.mkString(" ")

  val currentPos: Pos = visited.last

  val visitedAllBoard: Boolean = (visited.size == 64)

  /** What can be next possible knight moves in this travel.
   *
   * Contains all the possible next knight moves, if they reach the squares not yet visited.
   * */
  lazy val nextMoves = currentPos.knightMoves.filterNot(move => visited.contains(move))

  /** What can be next travels with one more knight move.
   *
   * Contains all the possible correct subsequent knight travels with one more next knight move.
   * */
  lazy val nextTravels = nextMoves.map(move => Travel(visited + move))

  /** From the current travel, try to find the solution to “knight travel” problem recursively.
   *
   * Return as soon as solution has been found.
   * */
  lazy val findKnightTravelSolution: Option[Travel] = {
    if (visitedAllBoard) {
      // Solution found in this branch of travels!
      Some(this)
    } else if (nextTravels.isEmpty) {
      // No solution in this branch of travels.
      None
    } else {
      // Generate the next travels and depth-search them.
      // We need to find only first solution; so it is LazyList
      // (may not get evaluated fully) and in it we find just the first nonempty item.
      LazyList.from(nextTravels)
        .map(tr => tr.findKnightTravelSolution)
        .find(_.nonEmpty)
        .flatten
    }
  }
}

object Travel {
  /** Constructor like `Travel(Pos("A4")).` */
  final def apply(startPos: Pos) = new Travel(startPos)

  /** Constructor like `Travel(Pos("D4"), Pos("E6"), Pos("F4")).` */
  final def apply(visited: Pos*) = new Travel(visited: _*)
}