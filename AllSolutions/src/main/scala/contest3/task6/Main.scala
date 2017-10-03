package contest3.task6

import scala.collection.mutable.ArrayBuffer

object Main extends App {
  val n = scala.io.StdIn.readLine().split(" ").map(_.toInt).apply(0)
  var arr = new Array[BigInt](n + 3)
  arr(0) = 2
  arr(1) = 4
  arr(2) = 7
  for (iter <- 3 until n) arr(iter) = Array(arr(iter - 1), arr(iter - 2) ,arr(iter - 3)).sum

  println(arr(n - 1))
}
