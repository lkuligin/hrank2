package contests.goldmansachs

object MaxStocks {

  case class StockPrice(price: Int, amountMax: Int)
  case class Target(amountBought: Long, moneyLeft: Long)

  def buyMaximumProducts(n: Int, k: Long, a: Array[StockPrice]): Long =  {
    val sortedDays: List[StockPrice] = a.sortWith(_.price < _.price).toList

    sortedDays.foldLeft((Target(0, k)))((t, price) => {
      val a = Math.min(t.moneyLeft / price.price, price.amountMax)
      Target(t.amountBought + a, t.moneyLeft - a * price.price)
    }).amountBought
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in)
    val n = sc.nextInt()
    var p = new Array[StockPrice](n)
    (1 to n).foreach(q => p(q-1) = StockPrice(sc.nextInt(), q))
    val k = sc.nextLong()
    val result = buyMaximumProducts(n, k, p)
    println(result)
  }
}
