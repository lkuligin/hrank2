package algo.search

import scala.annotation.tailrec
import scala.collection.immutable.HashSet

object CountPairs {

  def count(targetDiff: Int, intList: List[Int]): Int = {
    def check(el: Int, target: Int, set: Set[Int]): Int = {
      val firstCondition = if (el > target && set.contains(el-target)) 1 else 0
      val secondCondition = if (set.contains(el+target)) 1 else 0
      firstCondition + secondCondition
    }

    @tailrec
    def go(input: List[Int], pairs: Int, acc: Set[Int]): Int = input match {
      case Nil => pairs
      case v :: tail => go(tail, pairs + check(v, targetDiff, acc), acc + v)
    }

    go(intList, 0, HashSet())
  }

  def run(input: List[String]): Int = input match {
    case line1 :: line2 :: Nil => {
      val m: Int = line1.split("\\s").map(_.toInt).toList.tail.head
      val values: List[Int] = line2.split("\\s").map(_.toInt).toList
      count(m, values)
    }
    case _ => throw new IllegalArgumentException()
  }

  def main(args: Array[String]) {
    val res = run(io.Source.stdin.getLines.toList)

    println(res)
  }

}
