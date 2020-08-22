package com.myodov.knightwalk

import org.scalatest.FlatSpec

class KnightTravelSpec extends FlatSpec {
  "Knight travel" should "be printable" in {
    assertResult(
      "[F5 E3 C2 D4]"
    )(
      Travel(Pos("F5"), Pos("E3"), Pos("C2"), Pos("D4")).toString
    )
  }

  "Knight travel" should "return current position" in {
    assertResult(
      Pos("E7")
    )(
      Travel(Pos("E7")).currentPos
    )

    assertResult(
      Pos("B1")
    )(
      Travel(Pos("B1")).currentPos
    )

    assertResult(
      Pos("F4"),
    )(
      Travel(Pos("D4"), Pos("E6"), Pos("F4")).currentPos
    )
  }

  "Knight travel" should "calculate next moves" in {
    assertResult(
      List(Pos("E6"), Pos("F5"), Pos("F3"), Pos("E2"), Pos("C2"), Pos("B3"), Pos("B5"), Pos("C6")),
      "From a start position"
    )(
      Travel(Pos("D4")).nextMoves
    )

    assertResult(
      List(Pos("E6"), Pos("F5"), Pos("F3"), Pos("E2"), Pos("B3"), Pos("B5"), Pos("C6")),
      "From some travel"
    )(
      Travel(Pos("C2"), Pos("D4")).nextMoves
    )

    assertResult(
      List(Pos("E6"), Pos("F3"), Pos("E2"), Pos("B3"), Pos("B5"), Pos("C6")),
      "From some longer travel"
    )(
      Travel(Pos("F5"), Pos("E3"), Pos("C2"), Pos("D4")).nextMoves
    )
  }

  "Knight travel" should "calculate next travels" in {
    assertResult(
      List(
        Travel(Pos("D4"), Pos("E6")),
        Travel(Pos("D4"), Pos("F5")),
        Travel(Pos("D4"), Pos("F3")),
        Travel(Pos("D4"), Pos("E2")),
        Travel(Pos("D4"), Pos("C2")),
        Travel(Pos("D4"), Pos("B3")),
        Travel(Pos("D4"), Pos("B5")),
        Travel(Pos("D4"), Pos("C6"))
      ),
      "From a start position"
    )(
      Travel(Pos("D4")).nextTravels
    )

    assertResult(
      List(
        Travel(Pos("F5"), Pos("E3"), Pos("C2"), Pos("D4"), Pos("E6")),
        Travel(Pos("F5"), Pos("E3"), Pos("C2"), Pos("D4"), Pos("F3")),
        Travel(Pos("F5"), Pos("E3"), Pos("C2"), Pos("D4"), Pos("E2")),
        Travel(Pos("F5"), Pos("E3"), Pos("C2"), Pos("D4"), Pos("B3")),
        Travel(Pos("F5"), Pos("E3"), Pos("C2"), Pos("D4"), Pos("B5")),
        Travel(Pos("F5"), Pos("E3"), Pos("C2"), Pos("D4"), Pos("C6"))
      ),
      "From some longer travel"
    )(
      Travel(Pos("F5"), Pos("E3"), Pos("C2"), Pos("D4")).nextTravels
    )
  }
}
