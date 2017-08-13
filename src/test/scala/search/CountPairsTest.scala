package search

import org.scalatest.{MustMatchers, WordSpec}

class CountPairsTest extends WordSpec with MustMatchers {
  val o = CountPairs

  "ice cream parlor test 1" in {
    val input = "5 2  \n1 5 3 4 2  ".split("\n").toList
    o.run(input) mustBe 3
  }

}
