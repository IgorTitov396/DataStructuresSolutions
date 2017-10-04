package contest3.task5

import scala.collection.mutable

object Main extends App {
  implicit def arr2Tupl2(arr: Array[Int]): (Int, Int) = (arr(0), arr(1))

  val (n, k): (Int, Int) = scala.io.StdIn.readLine().split(" ").map(_.toInt)
  var arr = Array.ofDim[Array[Int]](n)
  for (i <- (0 until n)) arr(i) = scala.io.StdIn.readLine().split(" ").map(_.toInt)

  var arrAnswer = Array.ofDim[Int](n, k)

  arrAnswer(0)(0) = arr(0)(0)
  for (i <- 1 until n) arrAnswer(i)(0) = arr(i)(0) + arrAnswer(i - 1)(0)
  for (j <- 1 until k) arrAnswer(0)(j) = arr(0)(j) + arrAnswer(0)(j - 1)

  //for (iter <- 0 until n) println(arrAnswer(iter).mkString(" "))

  var minRng: Int = Int.MaxValue

  for (i <- 1 until n; j <- 1 until k) {
    val buf = math.min(arrAnswer(i - 1)(j), arrAnswer(i)(j - 1)) + arr(i)(j)
    arrAnswer(i)(j) = buf
  }

  //for (iter <- 0 until n) println(arrAnswer(iter).mkString(" "))

  println(arrAnswer(n - 1)(k - 1))
  println(k + n - 1)

  var (x, y) = (n - 1, k - 1)
  var st = mutable.Stack[(Int, Int)]((x, y))
  while (x != 0 && y != 0) {
    if (arrAnswer(x - 1)(y) <= arrAnswer(x)(y - 1)) x -= 1 else y -= 1
    st.push((x, y))
  }

  //for (iter <- 0 until n) println(arrAnswer(iter).mkString(" "))
  //println(s"X, Y: = $x, $y")

  if (x == 0) {
    while (y != 0) {
      y -= 1
      st.push((x, y))
    }
  }

  if (y == 0) {
    while (x != 0) {
      x -= 1
      st.push((x, y))
    }
  }

  while (st.nonEmpty) {
    var (answX, answY) = st.pop()
    answX += 1
    answY += 1
    println(s"$answX $answY")
  }
  //println()
  //for (iter <- 0 until n) println(arrAnswer(iter).mkString(" "))

}
