package algo.greedy

import scala.annotation.tailrec

/**
  * we will have buckets:
  * amount: first, middle, middle, ..., middle,            last
  * value:  A,     A-1,    A-2,    ..., A-middleBuckets, A - middleBuckets-1
  */

object AccessoryCollection {

  def solve(totalAmount: Int, typesAmount: Int, subsetSize: Int, diffInSubset: Int): Long = {
    def total(middle: Int): Long =
      if (middle < 0 ) -1
      else {
        val first: Long = subsetSize - middle * (diffInSubset - 2) - 1
        val middleCount: Long = (totalAmount - first) / middle
        val last: Long = totalAmount - first -  middle * middleCount
        if (first < middle
          || typesAmount - middleCount - (if (last > 0) 1 else 0) <= 0) -1
        else {
          val middleSum: Long = middleCount * (2 * typesAmount - middleCount - 1) / 2
          typesAmount * first + middleSum * middle + last * (typesAmount - middleCount - 1)
        }
      }

    @tailrec
    def go(maxObserved: Long, threshold: Int): Long = {
      if (threshold < 1.toLong) maxObserved
      else {
        val t = total(threshold)
        if (t > maxObserved) go(t, threshold - 1) else go(maxObserved, threshold - 1)
      }
    }

    if (typesAmount <= 0 || subsetSize < diffInSubset || subsetSize > totalAmount || totalAmount < diffInSubset) -1.toLong
    else if (diffInSubset == 1) typesAmount.toLong * totalAmount
    else go(-1, (subsetSize-1)/(diffInSubset-1))
  }

  def main(args: Array[String]) = {
    val sc = new java.util.Scanner(System.in)
    val t = sc.nextInt()
    (1 to t).foreach {
      _ => {
        val x = solve(sc.nextInt(), sc.nextInt(), sc.nextInt, sc.nextInt())
        println(if (x < 0) "SAD" else x)
      }
    }
  }
}
