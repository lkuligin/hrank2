package strings

import org.scalatest.{MustMatchers, WordSpec}

class SherlockValidStringTest extends WordSpec with MustMatchers {
  val o = SherlockValidString

  "sherlock valid strings" in {
    o.run("aabbcd") mustBe false
  }

  "sherlock valid strings 2" in {
    o.run("abcccc") mustBe false
  }

  "sherlock valid strings 3" in {
    o.run("hfchdkkbfifgbgebfaahijchgeeeiagkadjfcbekbdaifchkjfejckbiiihegacfbchdihkgbkbddgaefhkdgccjejjaajgijdkd") mustBe true
  }

}
