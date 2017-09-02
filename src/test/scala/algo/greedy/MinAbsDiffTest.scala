package algo.greedy

import org.scalatest.{MustMatchers, WordSpec}

class MinAbsDiffTest extends WordSpec with MustMatchers {

  val o = MinAbsDiff

  "min abs diff test 1" in {
    o.minAbsDiff(Array(3, -7, 0)) mustBe 3
  }

}
