package funcprog.memoization

import org.scalatest.{MustMatchers, WordSpec}
import DicePath._

class DicePathTest extends WordSpec with MustMatchers {

  "dice path" in {
    solve(List((2,2), (1,2), (2,1), (3,3)).map(i => Point(i._1, i._2))) mustBe List(9,4,6,19)
    }

}
