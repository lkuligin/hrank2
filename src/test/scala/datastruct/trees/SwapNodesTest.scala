package datastruct.trees

import org.scalatest.{MustMatchers, WordSpec}

class SwapNodesTest extends WordSpec with MustMatchers {
  val o = SwapNodes

  "swap test" in {
    val input = "2 3\n-1 -1\n-1 -1".split("\n")
    val tree = o.parse(input, 3)
    o.inOrder(tree) mustBe List(2,1,3)
    o.swapTree(Some(tree), 1, 1).map(o.inOrder).getOrElse(List()) mustBe List(3,1,2)
    o.swapTree(Some(tree), 1, 1).flatMap(x => o.swapTree(Some(x), 1, 1)).map(o.inOrder).getOrElse(List()) mustBe List(2,1,3)
  }

  "swap test 2" in {
    val input = "2 3\n-1 4\n-1 5\n-1 -1\n-1 -1".split("\n")
    val tree = o.parse(input, 5)
    o.swapTree(Some(tree), 2, 1).map(o.inOrder) mustBe Some(List(4,2,1,5,3))
  }

}
