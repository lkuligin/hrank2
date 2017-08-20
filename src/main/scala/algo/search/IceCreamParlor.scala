package algo.search

import java.lang.Math.{max, min}

import scala.annotation.tailrec
import scala.collection.immutable.HashMap

object IceCreamParlor {

  case class UnitCase(dollars: Int, flavors: Int, flavorCost: List[Int]) {
    def this(m: String, n: String, costs: String) = this(m.toInt, n.toInt, costs.split("\\s").map(_.toInt).toList)
  }

  type Result = (Int, Int)

  def parse(input: List[String]): (Int, List[UnitCase]) = {
    @tailrec
    def go(inp: List[String], acc: List[UnitCase]): List[UnitCase] = inp match {
      case Nil => acc
      case n :: m :: data :: tail => go(tail, new UnitCase(n, m, data) :: acc)
      case _ => throw new IllegalArgumentException()
    }

    (input.head.toInt, go(input.tail, List()))
  }

  def calculate(c: UnitCase): Option[Result] = {
    def go(inp: List[Int], i: Int, acc: HashMap[Int, Int]): Option[Result] = inp match {
      case Nil => None
      case el :: tail => if (acc.contains(c.dollars-el)) Some(new Result(i, acc.get(c.dollars-el).get)) else go(tail, i+1, acc + (el -> i))
    }

    go(c.flavorCost, 1, HashMap())
  }

  def run(input: List[String]): List[String] = {
    val (numberCases, testCases) = parse(input)

    testCases.reverse.map(calculate).map(x => x.map(y => s"${min(y._1, y._2)} ${max(y._1,y._2)}").getOrElse("na"))
  }

  def main(args: Array[String]) {
    val res = run(io.Source.stdin.getLines.toList).mkString("\n")

    println(res)
  }

}
