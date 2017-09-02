package contests.goldmansachs

import contests.goldmansachs.MaxStocks.StockPrice
import org.scalatest.{MustMatchers, WordSpec}

class MaxStocksTest extends WordSpec with MustMatchers {
  val o = MaxStocks

  "test 1" in {
    val input =  List((10,1), (7,2), (19, 3)).map(x => StockPrice(x._1, x._2)).toArray
    o.buyMaximumProducts(3, 45, input) mustBe 4
  }

}
