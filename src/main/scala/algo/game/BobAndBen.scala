package algo.game

/**
  * there is at least 1 leaf in any tree
  * if tree has > 2 nodes, than there is at least one non-leaf in a tree (since k>=2)
  * let's decode each tree with 1 or 2, and loosing position S = A1 ^ A2 ^ ... ^ An = 0
  *   1. decode with 1 if there is odd amount of nodes in the tree
  *   2. decode with 2 if there is even amount of nodes in the tree
  * from loosing position all moves lead to winning positions => obvious (we change at least 1 bit)
  * from winning position at least 1 move leads to loosing positions =>
  *   since we always add 2,1 or 0, we add 10, 01 or 00 so S should be 11, 10 or 01
  *   1. if S == 3 (11) than we have at least one 2 in the forest => change 2 -> 1 (remove a leaf) and S becomes zero
  *   2. if S == 1 (01) than we have at least one 1 in the forest => change 1 -> 0 (remove non-leaf) and S becomes 0
  *   3. if S == 2 (10) than we have at least one 2 in the forest => change 2 -> 0 (remove non-leaf) and S becomes 0
  * So we are not interested in k at all, we need only condition that k>=2!
  */

object BobAndBen {

  def solve(input: Game): Boolean =
    input.trees.foldLeft(0)((acc, el) => acc ^ (if (el % 2 == 1) 1 else if (el > 2) 2 else 0)) > 0

  case class Game (treesAmount: Int, trees: Array[Int]) //we are interested in amount on nodes only

  def main(args: Array[String]) = {
    val sc = new java.util.Scanner(System.in)
    val g = sc.nextInt()
    (0 to g-1).foreach(_ => {
      val n = sc.nextInt()
      val g = Game(n, new Array[Int](n))
      (0 to n-1).foreach(i => {
        g.trees(i) = sc.nextInt()
        val a = sc.nextInt()
      })
      println(if (solve(g)) "BOB" else "BEN")
    })
    sc.close()
  }

}
