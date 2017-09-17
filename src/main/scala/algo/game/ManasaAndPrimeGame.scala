package algo.game

import scala.annotation.tailrec

object ManasaAndPrimeGame {
  type SpragueGrandy = Int
  val maxPiles = 50 //Math.pow(10, 4).toInt

  val allowedAmounts = List(2,3,5,7,11,13)
  val spragueGrandyNumbers = new Array[Int](maxPiles+1)

  val spragueGrandyNumbersFixed = List(0,0,1,1,2,2,3,3,4)

  @tailrec
  private def mex(lst: List[Int], acc: Int): Int = lst match {
    case Nil => acc
    case head :: tail => if (head > acc) acc else mex(tail, acc+1)
  }

  def prepareSpragueGrandy(): Unit = {
    spragueGrandyNumbers(0) = 0 //terminal loosing position, grundy number is 0
    spragueGrandyNumbers(1) = 0 //terminal loosing position, grundy number is 0
    spragueGrandyNumbers(2)= 1 //last move is winning, grundy number is 1
    (3 to maxPiles).foreach(i => {
      val numbers = allowedAmounts.filter(x => (i-x)>=0).map(x => spragueGrandyNumbers(i-x))
      spragueGrandyNumbers(i) = mex(numbers.distinct.sorted, 0)
    })
  }

  def solve(input: Array[Long]): Boolean =
    input.foldLeft(0)((acc, el) => acc ^ spragueGrandyNumbersFixed((el % 9).toInt)) == 0

  def main(args: Array[String]) = {
    val sc = new java.util.Scanner(System.in)
    val t = sc.nextInt()
    (0 to t-1).foreach {_ =>
      val n = sc.nextInt()
      val input = new Array[Long](n)
      (0 to n-1).foreach(input(_) = sc.nextLong())
      println(if (solve(input)) "Sandy" else "Manasa")
    }
  }
}
