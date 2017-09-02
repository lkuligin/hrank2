package contests.goldmansachs

import scala.annotation.tailrec

object BankAccounts {

  def feeOrUpfront(n: Int, k: Int, x: Int, d: Int, p: List[Int]): String =  {
    val x1:Double = 1.0*x/100
    @tailrec
    def go(values: List[Int], acc: Double): Boolean = values match {
      case Nil => true
      case head :: tail => {
        val newAcc = acc + Math.max(k, head*x1)
        if (newAcc > d) false else go(tail, newAcc)
      }
    }
    if (go(p, 0.0)) "fee" else "upfront"
    //val com: Double = p.foldLeft(0.0)((acc, el) => acc + Math.max(k, x1*el))
    //if (com > d) "upfront" else "fee"
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in)
    val q = sc.nextInt()
    (1 to q).foreach(_ => {
      val n  = sc.nextInt()
      val k = sc.nextInt()
      val x = sc.nextInt()
      val d = sc.nextInt()
      val p: List[Int] = (1 to n).foldLeft(List[Int]())((lst, _) => sc.nextInt() :: lst)
      val result = feeOrUpfront(n, k, x, d, p)
      println(result)
    })
  }
}
