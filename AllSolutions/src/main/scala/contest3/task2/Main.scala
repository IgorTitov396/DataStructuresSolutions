package contest3.task2

import java.io.File

object Main extends App {
  implicit def arr2Tupl2(arr: Array[Int]): (Int, Int) = (arr(0), arr(1))

  val (n, k): (Int, Int) = scala.io.StdIn.readLine().split(" ").map(_.toInt)


  var arr = Array.ofDim[Int](n + 1, k + 1)
  arr(1)(1) = 1

  for (i <- (2 to n); j <- (2 to k)) if (i + j > 4) arr(i)(j) = arr(i - 2)(j - 1) + arr(i - 1)(j - 2)

  println(arr(n)(k))
}

