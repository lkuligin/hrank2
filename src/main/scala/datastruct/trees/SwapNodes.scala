package datastruct.trees

object SwapNodes {
  case class Node (value: Int, left: Option[Node], right: Option[Node])
  type Level = Int

  def parse(input: Array[String], l: Int) = {
    val inp: Array[Array[Option[Int]]] = input.map(_.split("\\s").map(_.toInt).map(x => if (x == -1) None else Some(x)))
    def getNode(i: Int): Node = {
      val nd: Array[Option[Int]] = inp(i-1)
      Node(i, nd(0).map(getNode), nd(1).map(getNode))
    }
    getNode(1)
  }


  def inOrder(root: Node): List[Int] = {
    def go(n: Node, acc: List[Int]): List[Int] = {
      val l: List[Int] = if (n.left.isDefined) go(n.left.get, acc) else List[Int]()
      val r: List[Int] = if (n.right.isDefined) go(n.right.get, acc) else List[Int]()
      l ::: n.value :: r
    }
    go(root, List())
  }

  def swapTree(root: Option[Node], level: Level, curLevel: Level): Option[Node] = root match {
    case None => None
    case Some(node) => {
      val l = if (curLevel % level == 0) node.right else node.left
      val r = if (curLevel % level == 0) node.left else node.right
      Some(Node(node.value, swapTree(l, level, curLevel+1), swapTree(r, level, curLevel+1)))
    }
  }

  def main(args: Array[String]) {
    val input: Array[String] = io.Source.stdin.getLines().toArray
    val n = input(0).toInt
    val tree = input.slice(1, n+1)
    val root: Node = parse(tree, n)
    val t = input(n+1).toInt
    val res = input.slice(n+2, n+3+t).foldLeft(Option(root))((tr, el) => {
      val e = swapTree(tr, el.toInt, 1)
      println(e.map(inOrder).map(_.mkString(" ")).get)
      e
    })
  }
}
