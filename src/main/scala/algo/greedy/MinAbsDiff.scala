package algo.greedy

import java.util.Scanner
import scala.util.Sorting.quickSort

object MinAbsDiff {

  case class MinDiff (diff: Int, previousEl: Int)

  def minAbsDiff(arr: Array[Int]): Int = {
    quickSort(arr)
    arr.tail.foldLeft(MinDiff(Int.MaxValue, arr.head))((md, el) => {
      val diff = Math.abs(el-md.previousEl)
      if (diff < md.diff) MinDiff(diff, el) else MinDiff(md.diff, el)
    } ).diff
  }

  def main(args: Array[String]) = {
    val sc = new Scanner(System.in)
    val n = sc.nextInt()
    val arr = new Array[Int](n)
    (0 to n-1).foreach(arr(_) = sc.nextInt())

    println(minAbsDiff(arr))
    sc.close()
  }
}
