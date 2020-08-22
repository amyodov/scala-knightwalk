package com.myodov.knightwalk

import org.scalatest.FlatSpec


class PosSpec extends FlatSpec {

  "Chess positions" should "be indexed properly" in {
    assertResult("A1")(Pos(0, 0).toString)
    assertResult("F3")(Pos(5, 2).toString)
    assertResult("H8")(Pos(7, 7).toString)
  }

  "Chess positions" should "be creatable from strings" in {
    assertResult(Pos(0, 0))(Pos("A1"))
    assertResult(Pos(5, 2))(Pos("F3"))
    assertResult(Pos(7, 7))(Pos("H8"))
  }

  "Knight moves" should "be correct in the middle of the board" in {
    assertResult(
      List(Pos("E6"), Pos("F5"), Pos("F3"), Pos("E2"), Pos("C2"), Pos("B3"), Pos("B5"), Pos("C6")),
      "Knight is in the middle of the board"
    )(
      Pos("D4").knightMoves
    )

    assertResult(
      List(Pos("D5"), Pos("E4"), Pos("E2"), Pos("D1"), Pos("B1"), Pos("A2"), Pos("A4"), Pos("B5")),
      "Knight is on the very edge of the board"
    )(
      Pos("C3").knightMoves
    )

    assertResult(
      List(Pos("G8"), Pos("H7"), Pos("H5"), Pos("G4"), Pos("E4"), Pos("D5"), Pos("D7"), Pos("E8")),
      "Knight is on another edge of the board"
    )(
      Pos("F6").knightMoves
    )
  }

  "Knight moves" should "be correct near the top and bottom of the board" in {
    assertResult(
      List(Pos("D4"), Pos("E3"), Pos("E1"), Pos("A1"), Pos("A3"), Pos("B4")),
      "Knight is too low"
    )(
      Pos("C2").knightMoves
    )

    assertResult(
      List(Pos("D3"), Pos("E2"), Pos("A2"), Pos("B3")),
      "Knight is way too low"
    )(
      Pos("C1").knightMoves
    )

    assertResult(
      List(Pos("E8"), Pos("E6"), Pos("D5"), Pos("B5"), Pos("A6"), Pos("A8")),
      "Knight is too high"
    )(
      Pos("C7").knightMoves
    )

    assertResult(
      List(Pos("E7"), Pos("D6"), Pos("B6"), Pos("A7")),
      "Knight is way too high"
    )(
      Pos("C8").knightMoves
    )
  }

  "Knight moves" should "be correct in the corners" in {
    assertResult(
      List(Pos("C4"), Pos("D3"), Pos("D1"), Pos("A4")),
      "Knight is in bottom left corner"
    )(
      Pos("B2").knightMoves
    )

    assertResult(
      List(Pos("B3"), Pos("C2")),
      "Knight is even closed to the bottom left corner"
    )(
      Pos("A1").knightMoves
    )
  }
}
