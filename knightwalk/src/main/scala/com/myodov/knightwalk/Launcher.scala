package com.myodov.knightwalk

object Launcher {
  def main(args: Array[String]) = {
    val initPos = Travel(Pos("A8"))
    val solution = initPos.findKnightTravelSolution
    if (solution.nonEmpty) {
      println(s"Solution is: ${solution.get}")
    } else {
      println("Solution is not found!")
    }
  }
}
