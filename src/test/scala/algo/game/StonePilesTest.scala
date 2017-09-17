package algo.game

import org.scalatest.{MustMatchers, WordSpec}

class StonePilesTest extends WordSpec with MustMatchers {

  val o = StonePiles
  println(o.spragueGrandyNumbers.toList)

  "stone piles test" in {
    o.solve(Array(4)) mustBe true
    o.solve(Array(1,2)) mustBe true
    o.solve(Array(1,3,4)) mustBe false
    o.solve(Array(8)) mustBe true
  }

}
