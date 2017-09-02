package contests.goldmansachs

import org.scalatest.{MustMatchers, WordSpec}

class BankAccountsTest extends WordSpec with MustMatchers {
  val o = BankAccounts

  "test 1" in {
    o.feeOrUpfront(3, 2, 10, 60, List(100,200,300)) mustBe "fee"
  }

  "test 2" in {
    o.feeOrUpfront(3, 2, 15, 120, List(200,250,300)) mustBe "fee"
  }
}
