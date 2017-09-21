package algo.greedy

import org.scalatest.{MustMatchers, WordSpec}

class AccessoryCollectionTest extends WordSpec with MustMatchers {
  val o = AccessoryCollection

  "accessory collection test" in {
    o.solve(6, 5, 3, 2) mustBe 24
    o.solve(9, 9, 4, 1) mustBe 81
    o.solve(2, 1, 2, 2) mustBe -1
    o.solve(9, 9, 4, 2) mustBe 72
    o.solve(9, 9, 4, 3) mustBe 53
    o.solve(9, 9, 4, 4) mustBe 45
    o.solve(9, 9, 5, 1) mustBe 81
    o.solve(9, 9, 5, 2) mustBe 75
    o.solve(9, 9, 5, 3) mustBe 65
    o.solve(9, 9, 5, 4) mustBe 53
    o.solve(9, 9, 5, 5) mustBe 45
    o.solve(9, 9, 6, 1) mustBe 81
    o.solve(9, 9, 6, 2) mustBe 77
    o.solve(9, 9, 6, 3) mustBe 69
    o.solve(9, 9, 6, 4) mustBe 60
    o.solve(9, 9, 6, 5) mustBe 53
    o.solve(9, 9, 6, 6) mustBe 45
    o.solve(9, 9, 7, 1) mustBe 81
    o.solve(9, 9, 7, 2) mustBe 78
    o.solve(9, 9, 7, 3) mustBe 72
    o.solve(9, 9, 7, 4) mustBe 66
    o.solve(9, 9, 7, 5) mustBe 60
    o.solve(9, 9, 7, 6) mustBe 53
    o.solve(9, 9, 7, 7) mustBe 45
  }

  "test case 7" in {
    o.solve(99930, 595328237, 3333, 10) mustBe "59491137279300".toLong
    o.solve(99963, 694924914, 99963, 99963) mustBe "69461782927479".toLong
    o.solve(98674, 7, 4022, 5) mustBe -1
    o.solve(99925, 7, 8, 3) mustBe -1
    o.solve(99919, 99920, 11560, 4979) mustBe "7567446674".toLong
    o.solve(99242, 71712, 71709, 71705) mustBe -1
    o.solve(97954, 264886637, 84348, 48978) mustBe "25944747293462".toLong
    o.solve(99010, 7542, 89762, 3771) mustBe "676298286".toLong
    o.solve(99986, 99990, 1, 1) mustBe "9997600140".toLong
    o.solve(98369, 713032393, 8801, 1462) mustBe "70139477701832".toLong
  }

}
