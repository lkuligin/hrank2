package funcprog.recursion

import java.util.Scanner
import scala.annotation.tailrec
import scala.util.Sorting.quickSort

object ConvexHull {

  case class Point(x: Double, y: Double)

  trait Orientation
  case object Collinear extends Orientation
  case object Clockwise extends Orientation
  case object Counterwise extends Orientation

  private def orientation(point1: Point, point2: Point, point3: Point): Orientation = {
    val prod = (point2.y - point1.y) * (point3.x - point2.x) - (point2.x - point1.x) * (point3.y - point2.y)
    if (prod == 0) Collinear
    else if (prod > 0) Clockwise else Counterwise
  }

  private def pointOrdering(bottom: Point) = new Ordering[Point] {
    def compare(x: Point, y: Point): Int = orientation(bottom, x, y) match {
      case Collinear => if (eqDist(bottom, x) >= eqDist(bottom, y)) 1 else -1
      case Counterwise => -1
      case Clockwise => 1
    }
  }

  private def eqDist(point1: Point, point2: Point): Double = Math.pow(Math.pow(point1.x - point2.x,2) + Math.pow(point1.y-point2.y, 2), 0.5)

  private def findMostBottom(input: Array[Point]): Point = {
    @tailrec
    def go(bottom: Point, ind: Int): Point =
      if (ind >= input.length) bottom
      else {
        go(
          if (bottom.y < input(ind).y) bottom
          else if (bottom.y == input(ind).y && bottom.x < input(ind).x) bottom
          else input(ind)
        , ind+1)
      }

    go(input(0), 1)
  }

  private[recursion] def convexHull(input: Array[Point]) = {
    quickSort(input)(pointOrdering(findMostBottom(input)))
    println(input.toList)

    @tailrec
    def go(acc: List[Point], currentPoint: Point, ind: Int): List[Point] =
      if (ind >= input.length) currentPoint :: acc
      else
      {
        val previousPoint = acc.head
        val nextPoint = input(ind)
        println(previousPoint, currentPoint, nextPoint, orientation(previousPoint, currentPoint, nextPoint))
        println(acc)
        orientation(previousPoint, currentPoint, nextPoint) match {
          case Collinear => go(acc, nextPoint, ind+1) //continue with currentPoint
          case Clockwise => go(acc.tail, previousPoint, ind) //reverse currentPoint
          case Counterwise => go(currentPoint :: acc, nextPoint, ind+1) //accept currentPoint
        }
      }

    go(List(input(0)), input(1), 2)
  }

  def solve(input: Array[Point]) = {
    val convexHullPath = convexHull(input)
    val begin = convexHullPath(0)
    val res = convexHullPath.foldLeft((0.0, begin))((result, point) => (result._1 + eqDist(result._2, point), point))
    res._1 + eqDist(begin, res._2)
  }

  def main(args: Array[String]) = {
    val sc = new Scanner(System.in)
    val n = sc.nextInt()
    val points = new Array[Point](n)
    (0 to n-1).foreach(points(_) = Point(sc.nextDouble(), sc.nextDouble()))
    sc.close()
    println(solve(points))
  }

}
