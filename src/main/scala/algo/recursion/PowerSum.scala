package algo.recursion

object PowerSum {
  def numberOfWays(x:Int, n:Int):Int = {
    def go(x: Int, acc: Int): Int = {
      val tmp =  (x - Math.pow(acc, n)).toInt
      tmp match {
        case 0 => 1
        case y if y < 0 => 0
        case y => go(tmp, acc+1) + go(x, acc+1)
      }
    }

    go(x, 1)
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    val a = sc.nextInt()
    val b = sc.nextInt()
    sc.close()
    println(numberOfWays(a,b))
  }

}
