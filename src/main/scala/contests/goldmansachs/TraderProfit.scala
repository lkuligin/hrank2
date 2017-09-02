package contests.goldmansachs

import scala.annotation.tailrec

object TraderProfit {

  case class Transaction(buy: Int, sell: Int)

  def potentialTransactions(a: List[Int]): List[Transaction] = {
    @tailrec
    def go(lst: List[Int], acc: List[Transaction]): List[Transaction] = lst match {
      case Nil => acc
      case _ :: Nil => acc
      case head :: head1 :: Nil => if (head < head1) Transaction(head, head1) :: acc else acc
      case head :: head1 :: head2 :: tail => {
        if (head >= head1) go(head1 :: head2 :: tail, acc)
        else if (head < head1 && head1 < head2 ) go(head :: head2 :: tail, acc)
        else go(head2 :: tail, Transaction(head, head1) :: acc)
      }
    }
    go(a, List())
  }

  def dropTransaction(t: List[Transaction]) = {
    val l = t.length
    @tailrec
    def scan(lst: List[Transaction], acc: Int, ind: Int, curInd: Int): Int = lst match {
      case Nil => ind
      case head :: Nil => if ((head.sell-head.buy) < acc) curInd else ind
      case head :: prev :: tail => {
        if (head.sell > prev.sell) {
          if (prev.sell-head.buy < acc) scan(prev::tail, prev.sell-head.buy, curInd, curInd+1)
          else scan(prev :: tail, acc, ind, curInd+1)
        }
        else {
          if (head.sell-head.buy < acc) scan(prev::tail, prev.sell-head.buy, curInd, curInd+1)
          else scan(prev :: tail, acc, ind, curInd+1)
        }
      }
    }

    @tailrec
    def go(lst: List[Transaction], acc: List[Transaction], ind: Int, targetInd: Int): List[Transaction] =  {
      if (ind != targetInd) go(lst.tail, lst.head :: acc, ind+1, targetInd)
      else lst match {
        case head :: prev :: tail => {
          val sell = Math.max(prev.sell, head.sell)
          acc.reverse ::: Transaction(prev.buy, sell):: tail
        }
        case _ => acc.reverse
      }
    }

    val worstInd = scan(t, Int.MaxValue, 0, 0)
    go(t, List(), 0, worstInd)
  }

  def estimate(t: List[Transaction], k: Int): List[Transaction] = {
    if (k >= t.length) t
    else {
      val a = dropTransaction(t)
      println(a)
      estimate(a, k)
    }
  }


  def traderProfit(k: Int, n: Int, a: List[Int]): Int =  {
    val p = potentialTransactions(a)
    println(p)
    estimate(p, k).foldLeft(0)((acc, el) => acc + el.sell - el.buy)
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    val q = sc.nextInt()
    (1 to q).foreach(_ => {
      val k = sc.nextInt()
      val n = sc.nextInt()
      val p: List[Int] = (1 to n).foldLeft(List[Int]())((lst, _) => sc.nextInt() :: lst)
      println(traderProfit(k, n, p))
    })
  }
}
