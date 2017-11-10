package funcprog.structures

import java.util.Scanner
import scala.annotation.tailrec

object StockPrediction {
  case class Query (day: Int, margin: Int)

  def solve(data: Array[Int], query: Query): Int = {
    val initialPrice = data(query.day)
    val maxPrice = query.margin + initialPrice

    @tailrec
    def goForward(acc: Int, day: Int): Int = {
      if (day >= data.length) acc
      else if (data(day) > maxPrice || data(day) < initialPrice) acc else goForward(acc+1, day+1)
    }

    @tailrec
    def goBackward(acc: Int, day: Int): Int = {
      if (day < 0) acc
      else if (data(day) > maxPrice || data(day) < initialPrice) acc else goBackward(acc+1, day-1)
    }

    goForward(0, query.day) + goBackward(0, query.day-1)
  }

  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val n = sc.nextInt()
    val input = new Array[Int](n)
    (0 to n-1).foreach(input(_) = sc.nextInt())
    val queriesAmount = sc.nextInt()
    val queries = new Array[Query](queriesAmount)
    (0 to queriesAmount-1).foreach(queries(_) = Query(sc.nextInt(), sc.nextInt()))

    val maxPrice = input.max
    val minPrice = input.min
    queries.map(query => if (query.margin > maxPrice && input(query.day) == minPrice) println(n) else println(solve(input, query)))

  }

}
