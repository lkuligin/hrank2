package datastruct.arrays

object TwoDimArraysDS {

  type Row = Array[Int]
  case class Block(maxObserved: Int, rows: Array[Row])

  def estimate(rows: Array[Row]) = rows(0).foldLeft(0)(_+_)+rows(2).foldLeft(0)(_+_)+rows(1)(1)

  def proceedHorizontally(b: Block): Int  = {
    def go(b: Block, acc: Int): Block = acc match {
      case 4 => b
      case i => go(Block(Math.max(b.maxObserved, estimate(b.rows.map(_.take(3)))), b.rows.map(_.tail)), i+1)
    }
    go(b, 0).maxObserved
  }

  def proceedVertically(inp: Array[Array[Int]]): Int = {
    def go(a: Array[Row], acc: Int, maxObserved: Int): Int = acc match {
      case 4 => maxObserved
      case i => go(a.tail, i+1, proceedHorizontally(Block(maxObserved, a.take(3))))
    }
    go(inp, 0, Int.MinValue)
  }

  def main(args: Array[String]) {
    val input: Array[Array[Int]] = io.Source.stdin.getLines.map(_.split("\\s").map(_.toInt)).toArray
    println(proceedVertically(input))
  }
}
