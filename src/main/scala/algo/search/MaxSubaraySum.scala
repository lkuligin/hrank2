package algo.search

import scala.annotation.tailrec

/**
  * prefix pi = a1+a2...ai mod m, and obviously pi < m
  * SUM(ai+...+aj) = if (pj-pi > 0) pj-pi else pj-pi+m = (pj-pi+m) mod m
  * pj-pi > pj only if pi > pj AND (pj-pi+m) mod m > pj,
  * so from all i < j we are interested only in min(pi such that pi>pj)
  * => if we sort the prefix array, we need to compare only sequential elements now!
  */

object MaxSubaraySum {

  case class OneTestCase(n: Int, m: Long, arr: List[Long])
  case class Evaluation(maxObserved: Long, lastEl: PrefixElement)
  case class PrefixElement(value: Long, index: Int)

  def evaluate(obs: OneTestCase): Long = obs.arr match {
    case List() => 0
    case el :: List() => el
    case head :: tail => {
      val prefix = tail
        .scanLeft(PrefixElement(head % obs.m, 0))((prefixEl, el) =>
          PrefixElement((prefixEl.value + (el % obs.m)) % obs.m, prefixEl.index+1))
        .sortWith(_.value > _.value)
      //println(prefix)
      prefix.tail.foldLeft(Evaluation(prefix.head.value,prefix.head))((eval, el) => {
        val newMax = Math.max(eval.maxObserved, el.value)
        val newMax1 = if (eval.lastEl.index < el.index)
          Math.max((el.value-eval.lastEl.value+obs.m) % obs.m, newMax)
        else newMax
        //println(el, eval, newMax, newMax1)
        Evaluation(newMax1, el)
      }).maxObserved
    }
  }

  def parse(input: List[String]): (Int, List[OneTestCase]) = {
    @tailrec
    def go(inp: List[String], acc: List[OneTestCase]): List[OneTestCase] = inp match {
      case Nil => acc
      case line1 :: data :: tail => {
        val Array(n, m) = line1.split("\\s").map(_.toLong)
        val query = data.split("\\s").map(_.toLong).toList
        if (query.length < n.toInt) throw new IllegalArgumentException() else go(tail, OneTestCase(n.toInt, m,  query) :: acc)
      }
      case _ => throw new IllegalArgumentException()
    }

    (input.head.toInt, go(input.tail, List()))
  }

  def run(input: List[String]): List[Long] = {
    val (numberCases, testCases) = parse(input)

    testCases.map(evaluate)
  }


  def main(args: Array[String]) {
    val res = run(io.Source.stdin.getLines.toList).reverse.mkString("\n")

    println(res)
  }
}
