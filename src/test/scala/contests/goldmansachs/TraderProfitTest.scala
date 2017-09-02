package contests.goldmansachs

import contests.goldmansachs.TraderProfit.Transaction
import org.scalatest.{MustMatchers, WordSpec}

class TraderProfitTest  extends WordSpec with MustMatchers {
  val o = TraderProfit

  "simplifying prices" in {
    o.potentialTransactions(List(10, 22, 5, 75, 65, 80)) mustBe
      List( Transaction(65, 80), Transaction(5, 75), Transaction(10, 22))
    o.potentialTransactions(List(11, 10, 22, 5, 6, 75, 65, 80, 75)) mustBe
      List( Transaction(65, 80), Transaction(5, 75), Transaction(10, 22))
    o.potentialTransactions(List(100, 90, 80, 50, 25)) mustBe List()
    o.potentialTransactions(List(10, 22, 30, 31, 40)) mustBe List(Transaction(10, 40))
  }

  "elimination worst transaction" in {
    val input = List(Transaction(10, 22), Transaction(5, 75), Transaction(65, 80), Transaction(70, 90))
    val step1 = o.dropTransaction(input.reverse)
    step1 mustBe List(Transaction(10, 22), Transaction(5, 75), Transaction(65, 90)).reverse
    o.dropTransaction(step1) mustBe List(Transaction(10, 22), Transaction(5, 90)).reverse
  }

  "trader profit 1" in {
    val input = List(10, 22, 5, 75, 65, 80)
    o.traderProfit(2, 6, input) mustBe 87
  }

  "trader profit 2" in {
    val input = List(20,580,420,900)
    o.traderProfit(3, 4, input) mustBe 1040
  }

  "trader profit 3" in {
    val input = List(2,2,2,50,40, 40, 60, 60, 60, 50)
    o.traderProfit(4, 6, input) mustBe 68
  }

  "trader profit 4" in {
    val input = List(2,5,4,10,6,8,12,14,16)
    o.traderProfit(4, 6, input) mustBe 19
    o.traderProfit(4, 6, List(2,5,4,10,6,8,12,11,16)) mustBe 20
  }
}
