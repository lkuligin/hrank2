package algo.game

import algo.game.BobAndBen.{Game}
import org.scalatest.{MustMatchers, WordSpec}

class BobAndBenTest extends WordSpec with MustMatchers {
  val o = BobAndBen

  "Ben&Bob game test 1" in {
    o.solve(Game(2, Array(1, 1))) mustBe false
  }

  "Ben&Bob game test 2" in {
    o.solve(Game(1, Array(3))) mustBe true
  }
}
