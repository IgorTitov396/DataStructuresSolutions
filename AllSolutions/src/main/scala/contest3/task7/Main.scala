package contest3.task7

object Main extends App {
  implicit def arr2Tupl2(arr: Array[Int]): (Int, Int) = (arr(0), arr(1))

  val (n, k): (Int, Int) = scala.io.StdIn.readLine().split(" ").map(_.toInt)
  var arr = Array.ofDim[Array[Int]](n)
  for (i <- (0 until n)) arr(i) = scala.io.StdIn.readLine().split(" ").map(_.toInt)

  var arrAnswer = Array.ofDim[Int](n, k)

  def func(rng: Int, arr: Array[Array[Int]], i: Int, j: Int): Int = {
    var (x, y, s) = (i - rng, j - rng, rng)
    var iter = 0
    while (arr(x + iter)(y + iter) == 0 && iter < s) {
      iter += 1
    }
    s - iter
  }

  for (i <- 0 until n) arrAnswer(i)(0) = arr(i)(0)
  for (j <- 0 until k) arrAnswer(0)(j) = arr(0)(j)

  //for (iter <- 0 until n) println(arrAnswer(iter).mkString)

  var maxRng: Int = 0

  for (i <- 1 until n; j <- 1 until k) {
    if (arr(i)(j) != 0) {
      val buf = func(math.min(arrAnswer(i - 1)(j), arrAnswer(i)(j - 1)), arr, i, j) + 1
      if (maxRng < buf) maxRng = buf
      arrAnswer(i)(j) = buf
    }
  }

  //println()
  //for (iter <- 0 until n) println(arrAnswer(iter).mkString)

  println(maxRng)
}