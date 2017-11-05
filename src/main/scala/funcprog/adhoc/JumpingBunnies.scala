package funcprog.adhoc

import java.util.Scanner
import scala.annotation.tailrec

object JumpingBunnies {

  @tailrec
  private [adhoc] def gcd(a: Long, b: Long): Long = if (b == 0) a else gcd(b, a % b)

  private def lcm(a: Long, b: Long): Long = (a/gcd(a,b))*b

  private def lcm(input: List[Long]): Long = input.reduceLeft(lcm(_, _))

  def run(input: List[Long]) = lcm(input).toString

  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val n = sc.nextLine().toInt
    val distances = sc.nextLine().split("\\s").map(_.toLong).toList

    println(run(distances))

  }

}
