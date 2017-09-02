package algo.bitmanipulation

/**
  * all we need is to find the most significant bit and then all bit are allowed to differ
  */

object MaxXor {

  def maxXor(l: Int, r: Int): Int = {
    val msb = 32 - Integer.numberOfLeadingZeros(l ^ r)
    1 << msb
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    val l:Int = sc.nextInt
    val r:Int = sc.nextInt
    sc.close()

    println(maxXor(l, r))
  }
}
