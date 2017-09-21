package funcprog.recursion

import funcprog.recursion.ConvexHull.{Point, solve, convexHull}
import org.scalatest.{MustMatchers, WordSpec}

class ConvexHullTest extends WordSpec with MustMatchers {

  /*"test1" in {
    val input = List((1,1), (2,5), (3,3), (5,3), (3,2), (2,2)).map(x => Point(x._1, x._2)).toArray
    solve(input) mustBe 12.2
  }*/

  "test ch" in {
    val input1 = List((1, 1), (2, 2), (3, 3), (1, 3)).map(x => Point(x._1, x._2)).toArray
    //println(convexHull(input1))
    val input2 = List[(Double, Double)]((4.4, 14), (6.7, 15.25), (6.9, 12.8), (2.1, 11.1), (9.5, 14.9), (13.2, 11.9), (10.3, 12.3), (6.8, 9.5), (3.3, 7.7),
      (0.6, 5.1), (5.3, 2.4), (8.45, 4.7), (11.5, 9.6), (13.8, 7.3), (12.9, 3.1), (11, 1.1)).map(x => Point(x._1, x._2)).toArray
    println(convexHull(input2))
  }

}
