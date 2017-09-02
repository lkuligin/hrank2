package algo.graphs

import org.scalatest.{MustMatchers, WordSpec}
import RealEstateBroker._

class RealEstateBrokerTest extends WordSpec with MustMatchers  {

  val o = RealEstateBroker

  "real estate test 1" in {
    o.solve(List((5,110),(9,500),(20,400)).map(x => Client(x._1, x._2)).toArray,
      List((10,100), (2, 200), (30, 300)).map(x => House(x._1, x._2)).toArray,
      3,
      3) mustBe 2
  }

}
