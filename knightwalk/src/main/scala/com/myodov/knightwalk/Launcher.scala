package com.myodov.knightwalk

object Launcher {
  def main(args: Array[String]) = {
    val initPos = Travel(Pos("A8"))
    val solution = initPos.findKnightTravelSolution
    println(s"Solution is: $solution")
  }
}
