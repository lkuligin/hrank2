package dynamic

import scala.annotation.tailrec

/**
  * 0 -> 0
  * 1 -> 1 2 -> 1 + 1 3 -> 2
  * xi+1 -> V.containt(xi+1) + (xi + x1) + (xi-1 + x2) + ...
  * = V.contains(xi+1) + ((xi-1 + x1) + (xi2 + x2)) + (xi-1 + x2) + ...
  *
  * coin Hi -> Hi+1
  */

object CoinChange {
  type Change = Int
  type Possibilities = Long
  type CoinChange = (Change, Possibilities)

  private[dynamic] def estimateOneCaseCoin(change: Int, coinValue: Int, acc: List[CoinChange]): Long = {

    def checkNewCoin(change: Int, coinValue: Int): Long =
      if (change % coinValue == 0 && coinValue <= change) 1 else 0

    val oldCoinsOnly: Long = acc.find(_._1 == change).map(_._2).getOrElse(0)

    checkNewCoin(change, coinValue) + oldCoinsOnly +
      acc.map(c => checkNewCoin(change - c._1, coinValue) * c._2).sum
  }


  def estimateOneMoreCoin(change: Int, coinValue: Int, acc: List[CoinChange]): List[CoinChange] = {
    List.range(1, change+1).map(x => (x, (estimateOneCaseCoin(x, coinValue, acc)))).filter(_._2 != 0)
  }

  def estimate(v: Int, values: List[Int]): Long = {
    @tailrec
    def go(change: Int, coinValues: List[Int], acc: List[CoinChange]): List[CoinChange] = coinValues match {
      case Nil => acc
      case head :: tail => go(change, tail, estimateOneMoreCoin(change, head, acc))
    }
    go(v, values, List()).filter(_._1 == v).headOption.map(_._2).getOrElse(0)
  }

  def run(input: List[String]): Long = {
    val n = input.head.split("\\s").map(_.toInt).head
    val values: List[Int] = input.tail.head.split("\\s").map(_.toInt).toList
    estimate(n, values)
  }

  def main(args: Array[String]) {
    val res = run(io.Source.stdin.getLines.toList)

    println(res)
  }

}
