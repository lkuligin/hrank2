package dynamic

import scala.annotation.tailrec
import Math.{max,abs}

object SherlockCost {
  /**
    * geometrically can prove that for any Ai, Ai+1, Ai+2 we have the property: Ai+1 should be either 1 or Bi+1
    * Xi = maximum diff for all arrays of A1 ... Ai-1 1 (=ending with 1)
    * Yi = maximum diff for all arrays of A1 ... Ai-1 Bi (=ending with Bi)
    * Xi+1 = max( [Xi 1], [Yi 1] ) = max( Xi, [Yi 1] ) = max( Xi, Yi + abs(1-Bi) )
    * Yi+1 = max( [Yi Bi+1], [Xi Bi+1]) ) = max ( Yi+abs(Bi-Bi+1), Xi
    */
  case class Evaluation(ends1: Int, endsB: Int, b: Int)

  private def evaluate(evaluation: Evaluation, nextB: Int): Evaluation = {
    val nextEnds1 = max(evaluation.ends1, evaluation.endsB + abs(1-evaluation.b))
    val nextEndsB = max(evaluation.endsB + abs(evaluation.b-nextB), evaluation.ends1 + abs(1-nextB))
    Evaluation(nextEnds1, nextEndsB, nextB)
  }

  def cost(inp: List[Int]): Int = {
    val e = inp.tail.foldLeft(Evaluation(0,0,inp.head))(evaluate(_, _))
    max(e.ends1, e.endsB)
  }


  def parse(input: List[String]): (Int, List[List[Int]]) = {
    @tailrec
    def go(inp: List[String], acc: List[List[Int]]): List[List[Int]] = inp match {
      case Nil => acc
      case n :: data => {
        val i = data.head.split("\\s").map(_.toInt).toList
        if (i.length < n.toInt) throw new IllegalArgumentException() else go(data.tail, i :: acc)
      }
      case _ => throw new IllegalArgumentException()
    }

    (input.head.toInt, go(input.tail, List()))
  }

  def run(input: List[String]): List[Int] = {
    val (numberCases, testCases) = parse(input)

    testCases.map(cost)
  }

  def main(args: Array[String]) {
    val res = run(io.Source.stdin.getLines.toList).reverse.mkString("\n")
    println(res)
  }

}
