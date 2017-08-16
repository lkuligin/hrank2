package datastruct.arrays

import org.scalatest.{MustMatchers, WordSpec}

class TwoDimArraysDSTest extends WordSpec with MustMatchers {
  val o = TwoDimArraysDS

  "2 dim arrays test" in {
    val input: Array[Array[Int]] = "1 1 1 0 0 0\n0 1 0 0 0 0\n1 1 1 0 0 0\n0 0 2 4 4 0\n0 0 0 2 0 0\n0 0 1 2 4 0".split("\n").map(_.split("\\s").map(_.toInt))
    o.proceedVertically(input) mustBe 19
  }

  "2 dim arrays test 1" in {
    val input: Array[Array[Int]] = "1 1 1 0 0 0\n0 1 0 0 0 0\n1 1 1 0 0 0\n0 9 2 -4 -4 0\n0 0 0 -2 0 0\n0 0 -1 -2 -4 0".split("\n").map(_.split("\\s").map(_.toInt))
    o.proceedVertically(input) mustBe 13
  }

}
