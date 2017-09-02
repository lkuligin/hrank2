package algo.graphs

/**
  * http://www.geeksforgeeks.org/maximum-bipartite-matching/
  */

object RealEstateBroker {

  case class House(val area: Long, val minPrice: Long)
  case class Client(val area: Long, val maxPrice: Long)

  def bpm(client: Int, houses: Array[Int]): Unit = {

  }

  def buildEdmondsMatrix(clients: Array[Client], houses: Array[House], clientsAmount: Int, housesAmount: Int): Array[Array[Boolean]] = {
    val res = Array.ofDim[Boolean](clientsAmount, housesAmount)

    (0 to clientsAmount-1).foreach( {
      c => (0 to housesAmount-1).foreach {
        h => if (houses(h).area > clients(c).area && houses(h).minPrice <= clients(c).maxPrice) res(c)(h) = true else res(c)(h) = false
      }
    })

    res
  }

  def bpm(adjMatrix: Array[Array[Boolean]], sales: Array[Int], clientId: Int, visited: Array[Boolean], houseId: Int, housesAmount: Int): Boolean =
    if (houseId < housesAmount) {
      if (adjMatrix(clientId)(houseId) && !visited(houseId)) {
        visited(houseId) = true

        if (sales(houseId) < 0 || bpm(adjMatrix, sales, sales(houseId), visited, 0, housesAmount)) {
          sales(houseId) = clientId
          true
        } else bpm(adjMatrix, sales, clientId, visited, houseId+1, housesAmount)
      } else bpm(adjMatrix, sales, clientId, visited, houseId+1, housesAmount)
    } else false

  def solve(clients: Array[Client], houses: Array[House], clientsAmount: Int, housesAmount: Int): Int = {
    val m = buildEdmondsMatrix(clients, houses, clientsAmount, housesAmount)

    val sales = Array.fill(housesAmount)(-1)

    def go(clientId: Int, acc: Int): Int = if (clientId < clientsAmount) {
      if (bpm(m, sales, clientId, Array.fill(housesAmount)(false), 0, housesAmount)) go(clientId + 1, acc+1)  else go(clientId + 1, acc)
    } else acc

    go(0, 0)
  }

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    val clientsAmount = sc.nextInt()
    val housesAmount = sc.nextInt()
    val clients = new Array[Client](clientsAmount)
    (0 to clientsAmount-1).foreach(i => clients(i) = Client(sc.nextLong(), sc.nextLong()))
    val houses = new Array[House](housesAmount)
    (0 to housesAmount-1).foreach(i => houses(i) = House(sc.nextLong(), sc.nextLong()))

    sc.close()

    println(solve(clients, houses, clientsAmount, housesAmount))
  }
}
