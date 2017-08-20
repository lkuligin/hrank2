package algo.dynamic

import java.io.ByteArrayInputStream
import org.scalatest.{MustMatchers, WordSpec}

class SherlockCostTest extends WordSpec with MustMatchers {
  val o = SherlockCost

  "sherlock cost test1" in {
    val input = "1\n5\n10 1 10 1 10".split("\n").toList
    o.run(input) mustBe List(36)
  }

  "sherlock cost test2" in {
    val input = "1\n5\n100 2 100 2 100".split("\n").toList
    o.run(input) mustBe List(396)
  }
}
