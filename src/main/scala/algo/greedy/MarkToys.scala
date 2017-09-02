package algo.greedy

import scala.annotation.tailrec
import scala.util.Sorting.quickSort

object MarkToys {

  case class Result(moneyLeft: Int, toysBought: Int)

  def solve(amount: Int, prices: Array[Int], capital: Int):Int = {
    @tailrec
    def go(acc: Result, pos: Int): Int = if (pos < amount) {
      if (acc.moneyLeft < prices(pos)) acc.toysBought else go(Result(acc.moneyLeft-prices(pos), acc.toysBought+1), pos+1)
    } else acc.toysBought

    quickSort(prices)
    go(Result(capital, 0), 0)
  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val n = sc.nextInt()
    val k = sc.nextInt()
    val prices = new Array[Int](n)
    (0 to n-1).foreach(prices(_) = sc.nextInt())
    sc.close()
    println(solve(n, prices, k))
  }
}
