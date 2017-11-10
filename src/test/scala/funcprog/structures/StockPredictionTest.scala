package funcprog.structures

import org.scalatest.{MustMatchers, WordSpec}
import StockPrediction._

class StockPredictionTest extends WordSpec with MustMatchers {

  "stock prediction" in {
    solve(Array(3, 5, 2, 6, 1), Query(0,2)) mustBe 2
    solve(Array(3, 5, 2, 6, 1), Query(2,3)) mustBe 3
  }

}
