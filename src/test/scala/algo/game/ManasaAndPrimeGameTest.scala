package algo.game

import org.scalatest.{MustMatchers, WordSpec}

class ManasaAndPrimeGameTest extends WordSpec with MustMatchers {

  val o = ManasaAndPrimeGame

  "solve" in {
    o.prepareSpragueGrandy()
    println(o.spragueGrandyNumbers.toList)
    o.solve(Array[Long](10,10)) mustBe true
    o.solve(Array[Long](2,2,3)) mustBe false
  }

}
