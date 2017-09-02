package algo.greedy

import org.scalatest.{MustMatchers, WordSpec}

class SherlockMinMaxTest extends WordSpec with MustMatchers {
  val o = SherlockMinMax

  "test case 1" in {
    o.solve(Array(5, 8, 14), 3, 4, 9) mustBe 4
  }

  "test case 2" in {
    o.solve(Array(1, 2, 5, 8, 14), 5, 4, 9) mustBe 4
  }

  "test case 3" in {
    o.solve(Array(1, 5, 8, 14), 4, 2, 12) mustBe 11
  }
}
