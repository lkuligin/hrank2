package dynamic

import java.io.ByteArrayInputStream
import org.scalatest.{MustMatchers, WordSpec}

class EqualTest extends WordSpec with MustMatchers {
  val o = Equal

  "equal test" in {
    val input = "1\n4\n2 2 3 7".split("\n").toList
    o.run(input) mustBe List(2)
  }

}
