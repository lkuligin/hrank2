package funcprog.adhoc

import org.scalatest.{MustMatchers, WordSpec}
import JumpingBunnies._

class JumpingBunniesTest extends WordSpec with MustMatchers {

  "gcd" in {
    gcd(10, 20) mustBe 10
    gcd(1071,462) mustBe 21
  }

  "run" in {
    JumpingBunnies.run(List(2,3,4).map(_.toLong)) mustBe "12"
  }


}
