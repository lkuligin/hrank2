package funcprog.adhoc

import org.scalatest.{MustMatchers, WordSpec}
import Mangoes._

class MangoesTest extends WordSpec with MustMatchers{
  "mangoes" in {
    solve(Array(2, 5, 3, 2, 4).map(_.toLong), Array(30,40,10,20,30).map(_.toLong), 5, 200) mustBe 3
    solve(Array(3,4).map(_.toLong), Array(1,2).map(_.toLong), 2, 100) mustBe 2
    solve(Array(2, 5, 3, 2, 4).map(_.toLong), Array(30,40,10,20,30).map(_.toLong), 5, 1) mustBe 0
  }

}
