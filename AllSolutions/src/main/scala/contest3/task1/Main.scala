package contest3.task1

import java.io.File

object Main extends App {
  val input = scala.io.Source.fromFile("grasshopper.in")
  val out = new java.io.PrintWriter(new File("grasshopper.out"))

  implicit def arr2Tupl2(arr: Array[Int]): (Int, Int) = (arr(0), arr(1))

  val (n, k): (Int, Int) = input.getLines().next().split(" ").map(_.toInt)

  var arr = Array[Int](1)

  for (i <- (1 until n)) arr = arr ++ Array(arr.takeRight(math.min(k, i)).sum)

  out.print(arr.last)

  input.close()
  out.close()
}
