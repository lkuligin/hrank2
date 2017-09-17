package algo.game

import scala.annotation.tailrec

/**
  * the most nice property = if we already now the sprague-grandy value for a pile with N amount of stones, we do not to split it anymore
  */

object StonePiles {

  type SpragueGrandy = Int
  val maxStonesInPile = 50

  val spragueGrandyNumbers = new Array[Int](maxStonesInPile+1)
  prepareSpragueGrandy()

  @tailrec
  private def mex(lst: List[Int], acc: Int): Int = lst match {
    case Nil => acc
    case head :: tail => if (head > acc) acc else mex(tail, acc+1)
  }

  def splitValue(initialValue: Int): List[SpragueGrandy] = {

    def go(value: Int, threshold: Int, accXor: Int, acc: List[SpragueGrandy]): List[SpragueGrandy] = {
      if (threshold > value / 2 || value-threshold <= threshold) acc
      else
        go(value - threshold, threshold + 1, accXor ^ spragueGrandyNumbers(threshold),
            (accXor ^ spragueGrandyNumbers(threshold) ^
              spragueGrandyNumbers(value - threshold)) :: acc) :::
            go(value, threshold + 1, accXor, List())
    }
    go(initialValue, 1, 0, List())
  }

  def prepareSpragueGrandy(): Unit = {
    spragueGrandyNumbers(0) = 0 //terminal loosing position, grundy number is 0
    spragueGrandyNumbers(1) = 0 //terminal loosing position, grundy number is 0
    spragueGrandyNumbers(2)= 0 //terminal loosing position, grundy number is 0
    (3 to maxStonesInPile).foreach(i => {
      val numbers = splitValue(i)
      spragueGrandyNumbers(i) = mex(numbers.distinct.sorted, 0)
    })
  }

  //whoever moves first looses the game
  def solve(input: Array[Int]): Boolean = {
    input.foldLeft(0)((acc, el) => acc ^ spragueGrandyNumbers(el)) == 0
  }

  def main(arg: Array[String]) = {
    val sc = new java.util.Scanner(System.in)
    val t = sc.nextInt()
    (0 to t-1).foreach(_ => {
      val n = sc.nextInt()
      val input = new Array[Int](n)
      (0 to n-1).foreach(input(_)=sc.nextInt())
      println(if (solve(input)) "BOB" else "ALICE")
    })
  }

}
