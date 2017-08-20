package algo.search

import org.scalatest.{MustMatchers, WordSpec}

class IceCreamParlorTest extends WordSpec with MustMatchers {
  val o = IceCreamParlor

  "ice cream parlor test 1" in {
    val input = "2\n4\n5\n1 4 5 3 2\n4\n4\n2 2 4 3".split("\n").toList
    o.run(input) mustBe List("1 4", "1 2")
  }

}
