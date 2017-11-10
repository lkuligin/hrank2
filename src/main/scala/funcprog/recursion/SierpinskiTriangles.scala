package funcprog.recursion

import java.util.Scanner

object SierpinskiTriangles {
  private val triangleWidth = 63
  private val triangleHeight = 32

  case class Point (x: Int, y: Int)
  case class Triangle(upNode: Point, size: Int) {
    def leftNode: Point = Point(upNode.x-size, upNode.y+size)
    def rightNode: Point = Point(upNode.x+size, upNode.y+size)
    def nodes: List[Point] = List(upNode, leftNode, rightNode)
  }

  def build(n: Int): List[Triangle] = {
    def go(acc: List[Triangle], n: Int): List[Triangle] = if (n == 1) acc else
      go(acc.flatMap(t => {
        val newUpTriangle = Triangle(t.upNode, t.size/2)
        newUpTriangle :: List(newUpTriangle.leftNode, newUpTriangle.rightNode).map(Triangle(_, t.size/2))
      }), n-1)

    go(List(Triangle(Point((triangleWidth+1)/2, 1), triangleHeight)), n)
  }

  def draw(n: Int): Unit = draw(build(n))

  def draw(triangles: List[Triangle]) = {
    val initialPicture: Array[Array[Char]] = Array.fill(triangleHeight)(Array.fill(triangleWidth)('_'))

    triangles.foreach(triangle => {
      (1 to triangle.size).foreach(row => {
        (-(row-1) to (row-1)).foreach(column => initialPicture(row+triangle.upNode.y-2)(triangle.upNode.x+column-1)='1')
      })
    })

    initialPicture.map(row => println(row.mkString("")))
  }

  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)

    val n = sc.nextInt()
    draw(n)
  }

}
