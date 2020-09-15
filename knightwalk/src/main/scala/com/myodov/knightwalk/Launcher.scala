package com.myodov.knightwalk

object Launcher {
  val ITERATIONS = 1000

  def main(args: Array[String]) = {
    val initPos = Travel(Pos("A8"))

    // Benchmark

    val startTime = System.nanoTime
    val allResultValid = Range(0, ITERATIONS)
      .map(_ => initPos.findKnightTravelSolution)
      .forall(res => res.nonEmpty)
    val endTime = System.nanoTime

    val durationSeconds = (endTime - startTime) / 1_000_000_000.0
    val tps = ITERATIONS / durationSeconds
    val secPerIter = durationSeconds / ITERATIONS

    println(s"Tried $ITERATIONS iterations, all knight travels found: $allResultValid.")
    println(f"$tps%.2f solutions per second, each took $secPerIter%.6f sec.")

    // Find reference solution
    val solution = initPos.findKnightTravelSolution

    if (solution.nonEmpty) {
      println(s"Solution is: ${solution.get}")
    } else {
      println("Solution is not found!")
    }
  }
}
