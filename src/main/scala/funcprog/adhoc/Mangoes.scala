package funcprog.adhoc

import java.util.Scanner
import scala.annotation.tailrec

object Mangoes {

  def solve(appetite: Array[Long], happiness: Array[Long], friendsAmount: Long, mangosAmount: Long): Long = {
    @tailrec
    def go(friendsMin: Long, friendsMax: Long): Long = if (friendsMin == friendsMax) friendsMin else {
      val threshold = (friendsMax + friendsMin +1) / 2
      val mangosConsumed = appetite.zip(happiness).map(x => x._1 + x._2 * (threshold-1)).sorted.take(threshold.toInt).sum
      if (mangosConsumed > mangosAmount) go(friendsMin, threshold-1) else if (mangosConsumed == mangosAmount) threshold else go(threshold, friendsMax)
    }
    go(0.toLong, friendsAmount)
  }

  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val friendsAmount = sc.nextInt()
    val mangosAmount = sc.nextLong()
    val appetite = new Array[Long](friendsAmount)
    val happiness = new Array[Long](friendsAmount)
    (0 to friendsAmount-1).foreach(appetite(_) = sc.nextLong())
    (0 to friendsAmount-1).foreach(happiness(_) = sc.nextLong())

    println(solve(appetite, happiness, friendsAmount, mangosAmount))

  }
}
