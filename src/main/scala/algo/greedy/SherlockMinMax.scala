package algo.greedy

import scala.annotation.tailrec
import scala.util.Sorting.quickSort

object SherlockMinMax {

  case class Result(maxObs: Int, value: Int)

  def solve(input: Array[Int], n: Int, minRange: Int, maxRange: Int) = {
    @tailrec
    def go(ind: Int, acc: Result): Result = if (ind < n - 1) {
      val prevEl = input(ind)
      val el = input(ind + 1)

      if (prevEl > maxRange) acc
      else if (el < minRange) go(ind+1, acc)
      else {
        val middle = (el + prevEl) / 2
        val r = {
          if (middle > maxRange) Result(Math.abs(maxRange - prevEl), maxRange)
          else if (middle < minRange) Result(Math.abs(minRange - el), minRange)
          else Result(Math.abs(prevEl - middle), middle)
        }
        if (r.maxObs > acc.maxObs) go(ind + 1, r) else go(ind + 1, acc)
      }
    } else acc

    quickSort(input)
    val r1 = go(0, Result(Int.MinValue, 0))
    val r2 = if (minRange < input(0)) Result(Math.abs(minRange-input(0)), minRange) else Result(Int.MinValue, 0)
    val r3 = if (maxRange > input(n-1)) Result(Math.abs(maxRange-input(n-1)), maxRange) else Result(Int.MinValue, 0)
    if (r2.maxObs >= r1.maxObs && r2.maxObs >= r3.maxObs) r2.value
    else if (r1.maxObs >= r2.maxObs && r1.maxObs >= r3.maxObs) r1.value
    else r3.value

  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val n = sc.nextInt()
    val input = new Array[Int](n)
    (0 to n - 1).foreach(input(_) = sc.nextInt())
    val minRange = sc.nextInt()
    val maxRange = sc.nextInt()
    sc.close()
    println(solve(input, n, minRange, maxRange))
  }

}
