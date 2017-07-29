package dynamic
import annotation.tailrec

object Equal {

  private def reduceUser(amount: Int): Int = amount / 5 + (amount % 5) / 2 + (amount % 5 % 2)

  def equal(input: List[Int]) = {
    lazy val minC = input.min
    List(0,1,2,5).map(x => input.map(_ - minC + x).foldLeft(0)(_+reduceUser(_))).min

  }

  def parse(input: List[String]): (Int, List[(Int, List[Int])]) = {
    @tailrec
    def go(inp: List[String], acc: List[(Int, List[Int])]): List[(Int, List[Int])] = inp match {
      case Nil => acc
      case n :: data => {
        val i = data.head.split("\\s").map(_.toInt).toList
        if (i.length < n.toInt) throw new IllegalArgumentException() else go(data.tail, (n.toInt, i) :: acc)
      }
      case _ => throw new IllegalArgumentException()
    }

    (input.head.toInt, go(input.tail, List()))
  }

  def run(input: List[String]): List[Int] = {
    val (numberCases, testCases) = parse(input)

    testCases.map(_._2).map(equal)
  }

  def main(args: Array[String]) {
    val res = run(io.Source.stdin.getLines.toList).reverse.mkString("\n")
    println(res)
  }
}
