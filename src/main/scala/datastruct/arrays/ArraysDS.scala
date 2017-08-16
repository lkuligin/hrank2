package datastruct.arrays

object ArraysDS {

  def main(args: Array[String]) {
    println(io.Source.stdin.getLines.toList.tail.head.split("\\s").map(_.toInt).reverse.mkString("\\s"))
  }
}
