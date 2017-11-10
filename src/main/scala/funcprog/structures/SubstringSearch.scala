package funcprog.structures

import java.util.Scanner
import scala.annotation.tailrec

object SubstringSearch {

  case class UseCase(text: Array[Char], pattern: Array[Char])

  def kmpTable(pattern: Array[Char]): Array[Int] = {

    val table = new Array[Int](pattern.length)
    table(0) = -1

    @tailrec
    def newCnd(cnd: Int, pos: Int): Int = if (cnd >= 0 && !(pattern(pos) == pattern(cnd))) newCnd(table(cnd), pos) else cnd

    @tailrec
    def go(cnd: Int, pos: Int): Unit = if (pos >= pattern.length) {} else {
      if (pattern(pos) == pattern(cnd)) {
        table(pos) = table(cnd)
        go(cnd+1, pos+1)
      } else {
        table(pos) = cnd
        go(newCnd(table(cnd), pos)+1, pos+1)
      }
    }

    go(0, 1)
    table

  }

  def kmpSearch(useCase: UseCase): Boolean = {
    val table = kmpTable(useCase.pattern)

    @tailrec
    def go(m: Int, i: Int): Boolean = {
      if (m+i >= useCase.text.length) false else {
        if (useCase.pattern(i) == useCase.text(m+i))
          if (i+1 == useCase.pattern.length) true else go(m, i+1)
        else if (table(i) > -1) go(m+i-table(i), table(i))
        else go(m+i+1, 0)
      }
    }

    go(0, 0)
  }

  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val usesCasesAmount = sc.nextLine().toInt

    (0 to usesCasesAmount-1).foreach(_ =>
      println(if (kmpSearch(UseCase(sc.nextLine().trim.toCharArray, sc.nextLine().trim.toCharArray))) "YES" else "NO"))
  }

}
