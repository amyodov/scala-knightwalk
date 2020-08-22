package com.myodov.knightwalk

/** Position (square) on the chess board. */
case class Pos(x: Int, y: Int) {
  require(Pos.INDICES contains x, s"$x is not between 0 and 7")
  require(Pos.INDICES contains y, s"$y is not between 0 and 7")

  override def toString: String = s"${Pos.FILES(x)}${y + 1}"

  /** Knight move (one of 8 possible ones) from this position. */
  final def knightMove(moveIndex: Int): Option[Pos] = {
    require(Pos.INDICES contains moveIndex)

    Pos.knight_mover(moveIndex)(x, y) match {
      case (newX, newY) if (Pos.INDICES contains newX) && (Pos.INDICES contains newY) => Some(Pos(newX, newY))
      case _ => None
    }
  }

  /** All possible knight moves from this position. */
  lazy val knightMoves: List[Pos] = (0 to 7).flatMap(i => knightMove(i)).toList
}


object Pos {
  /** The horizontal index of chessboard is called “file”. */
  private final val FILES = ('A' to 'H').toArray

  /** The vertical index of chessboard is called “rank”. */
  private final val RANKS = (1 to 8).toArray

  private final val INDICES: Range.Inclusive = 0 to 7

  val POSITIONS = Array.ofDim[Pos](INDICES.end + 1, INDICES.end + 1)
  for (x <- INDICES; y <- INDICES) {
    POSITIONS(x)(y) = new Pos(x, y)
  }

  /** For an index of knight move, calculate how it should move. */
  final def knight_mover(i: Int) = {
    i match {
      case 0 => (x: Int, y: Int) => (x + 1, y + 2)
      case 1 => (x: Int, y: Int) => (x + 2, y + 1)
      case 2 => (x: Int, y: Int) => (x + 2, y - 1)
      case 3 => (x: Int, y: Int) => (x + 1, y - 2)
      case 4 => (x: Int, y: Int) => (x - 1, y - 2)
      case 5 => (x: Int, y: Int) => (x - 2, y - 1)
      case 6 => (x: Int, y: Int) => (x - 2, y + 1)
      case 7 => (x: Int, y: Int) => (x - 1, y + 2)
    }
  }

  /** All positions on the board are pre-cached for performance. */
  final def apply(x: Int, y: Int): Pos = POSITIONS(x)(y)

  final def apply(str: String): Pos = {
    val x = Pos.FILES.indexOf(str.charAt(0))
    val y = str.substring(1).toInt - 1
    Pos(x, y)
  }
}
