package dynamic

import java.io.ByteArrayInputStream
import org.scalatest.{MustMatchers, WordSpec}

class CoinChangeTest extends WordSpec with MustMatchers {

  val o = CoinChange

  "estimateOneCaseCoin" in {
    o.estimateOneCaseCoin(1,1,List()) mustBe 1
    o.estimateOneCaseCoin(1,2,List()) mustBe 0
    o.estimateOneCaseCoin(3,2,List()) mustBe 0
    o.estimateOneCaseCoin(3,2,List((1,1))) mustBe 1
    o.estimateOneCaseCoin(5,3,List((1,1), (2,3))) mustBe 3
    o.estimateOneCaseCoin(7,3,List((1,1), (4,2))) mustBe 3
    o.estimateOneCaseCoin(20,5,List((10,2), (4,2))) mustBe 3
    o.estimateOneCaseCoin(20,5,List((10,2), (4,2), (20,2))) mustBe 5
  }

  "estimateOneMoreCoin" in {
    o.estimateOneMoreCoin(1,1,List()) mustBe List((1,1))
    o.estimateOneMoreCoin(1,2,List()) mustBe List()
    o.estimateOneMoreCoin(3,2,List()) mustBe List((2,1))
    o.estimateOneMoreCoin(3,2,List((1,1))) mustBe List((1,1),(2,1),(3,1))
    o.estimateOneMoreCoin(5,3,List((1,1), (2,3))) mustBe List((1,1),(2,3),(3,1),(4,1),(5,3))
  }

  "coin change test" in {
    val input = "4 3\n1 2 3".split("\n").toList
    o.run(input) mustBe 4
  }

  "coin change test 2" in {
    val input = "10 4\n2 5 3 6".split("\n").toList
    o.run(input) mustBe 5
  }

  "coin change test 3" in {
    val input = "1 25\n48 6 34 50 49 36 30 35 40 41 17 43 39 13 4 20 19 2 46 7 38 33 28 18 21".split("\n").toList
    o.run(input) mustBe 0
  }

  "coin change test 4" in {
    val input = "250 24\n41 34 46 9 37 32 42 21 7 13 1 24 3 43 2 23 8 45 19 30 29 18 35 11".split("\n").toList
    o.run(input).toString mustBe "15685693751"
  }

}
