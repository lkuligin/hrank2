package contests.goldmansachs

import scala.annotation.tailrec
import scala.collection.immutable.TreeSet

class TimeSeriesQuery {

  case class Point(time: Int, price: Int)

  val sortByPrice = new Ordering[Point] {
    def compare(p1: Point, p2: Point) = if (p1.price <= p2.price) -1 else +1
  }

  val sortByTime = new Ordering[Point] {
    def compare(p1: Point, p2: Point) = if (p1.time <= p2.time) -1 else +1
  }

  def firstTime(values: TreeSet[Point]): Int = {
    val a = values.map(_.time)
    if (a.isEmpty) -1 else a.min
  }

  def maxValue(values: TreeSet[Point]): Int = {
    val a = values.map(_.price)
    if (a.isEmpty) -1 else a.max
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    val n = sc.nextInt()
    val q = sc.nextInt()
    val t: List[Int] = (1 to n).foldLeft(List[Int]())((lst, _) => sc.nextInt() :: lst)
    val p: List[Int] = (1 to n).foldLeft(List[Int]())((lst, _) => sc.nextInt() :: lst)
    val data = t.zip(p).map(x => Point(x._1,x._2))
    val t1 = collection.immutable.TreeSet(data :_*)(sortByPrice)
    val t2 = collection.immutable.TreeSet(data :_*)(sortByTime)
    println(t1)
    println(t2)
    (1 to q).foreach(_ => {
      val tp = sc.nextInt()
      val qr = sc.nextInt()

      val ans = if (tp == 1) firstTime(t1.filter(_.price >= qr)) else maxValue(t2.filter(_.time >= qr))
      println(ans)
    })
  }
}
