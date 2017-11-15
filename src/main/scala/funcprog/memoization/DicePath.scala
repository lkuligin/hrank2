package funcprog.memoization

import java.util.Scanner
import scala.collection.mutable

object DicePath {

  val boarderSize = 60

  case class Point(x: Int, y: Int)
  type Record = mutable.Map[Dice, Int]

  case class Dice(top: Int, left: Int, front: Int) {
    def right = 7-left
    def back = 7-front
    def bottom = 7-top
    def moveRight = Dice(right, top, front)
    def moveDown = Dice(back, left, top)
  }

  def initialDice = Dice(1, 4, 2) // top = 1, left = 4, front = 2, right = 3, back = 5, bottom = 6
  //right = (3, 1, 2)
  //down = (5, 4, 1)

  private def getCache(): Array[Array[Record]] = {
    val cache: Array[Array[Record]] = Array.fill(boarderSize)(Array.fill(boarderSize)(mutable.Map[Dice, Int]()))

    (0 to boarderSize-1).foreach(y => {
      if (y == 0) {cache(0)(0)(initialDice) = 1} else {
        val dices = cache(0)(y-1).keys.toList
        dices.foreach(dice => {
          val newDice = dice.moveDown
          val newSum = cache(0)(y-1)(dice) + newDice.top
          //println("y down", 0, y, newDice, newSum)
          if (!(cache(0)(y).getOrElse(newDice, 0) > newSum)) {cache(0)(y)(newDice) = newSum}
        })
      }
      (1 to boarderSize-1).foreach(x => {
        cache(x-1)(y).keys.foreach(dice => {
          val newDice: Dice = dice.moveRight
          val newSum: Int = cache(x-1)(y)(dice) + newDice.top
          //println("right", x, y, newDice, newSum)
          if (!(cache(x)(y).getOrElse(newDice, 0) > newSum)) {cache(x)(y)(newDice) = newSum}
        })
        if (y > 0) {
          cache(x)(y-1).keys.foreach(dice => {
            val newDice: Dice = dice.moveDown
            val newSum: Int = cache(x)(y-1)(dice) + newDice.top
            //println("down", x, y, newDice, newSum)
            if (!(cache(x)(y).getOrElse(newDice, 0) > newSum)) {cache(x)(y)(newDice) = newSum}
          })
        }
      })
    })

    cache
  }

  def solve(input: List[Point]): List[Int] = {
    val cache = getCache()
    //println(cache(1)(0))
    //println(cache(0)(1))
    //println(cache(1)(1))
    input.map(point => cache(point.y-1)(point.x-1).values.max)
  }

  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)

    val testCasesAmount = sc.nextInt()
    
    val input: List[Point] = List.fill(testCasesAmount)(Point(sc.nextInt(), sc.nextInt()))

    solve(input).foreach(i => println(i))
  }

}
