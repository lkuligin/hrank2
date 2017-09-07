package algo.game

object MisereNim {
  case class Game(piles: Int, stones: List[Int])

  def solve(input: Game): Boolean = {
    val stonesAmount = input.stones.foldLeft(0)(_ + _)
    val stonesXor = input.stones.foldLeft(0)(_ ^ _)
    if (stonesAmount == input.stones.length) (stonesAmount % 2 == 0)
    else (stonesXor > 0)
  }

  def main(args: Array[String]) = {
    val sc = new java.util.Scanner(System.in)
    val t = sc.nextInt()
    val testCases = new Array[Game](t)
    (0 to t-1).foreach(testCases(_) = {
      val n = sc.nextInt()
      val l = List.fill(n)(1).foldLeft(List[Int]())((acc, _) => sc.nextInt() :: acc).reverse
      Game(n, l)
    })
    sc.close()

    (0 to t-1).foreach(x => println(if (solve(testCases(x))) "First" else "Second"))
  }
}
