package strings

import scala.annotation.tailrec
import scala.collection.mutable

object SherlockValidString {

  def checkOccurence(it: List[Int]): Boolean = {
    @tailrec
    def go(occ: Option[Int], firstCount: Boolean, firstRemove: Boolean, values: List[Int]): Boolean = values match {
      case Nil => true
      case head :: tail => occ match {
        case Some(o) => {
          //println(o, head, firstCount, firstRemove)
          if (o == head) go(occ, false, firstRemove, tail)
          else if (firstRemove && (head == 1 || Math.abs(head-o) == 1)) go(occ, false, false, tail)
          else if (firstRemove && firstCount && o == 1) go(Some(head), false, false, tail)
          else false
        }
        case None => go(Some(head), true, firstRemove, tail)
      }
    }

    go(None, true, true, it)
  }

  def countOccurence(s: String): Map[Char, Int] = {
    val occ = scala.collection.mutable.HashMap[Char, Int]()
    s.foreach(x => occ.put(x, 1+occ.getOrElse(x, 0)))
    occ.toMap
  }

  def run(s: String) = {
    val a = countOccurence(s)
    //println(a)
    checkOccurence(a.values.toList)
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in)
    val s = sc.next()
    val res = if (run(s)) "YES" else "NO"
    println(res)
  }
}
