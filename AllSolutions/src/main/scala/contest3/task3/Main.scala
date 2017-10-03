package contest3.task3

object Main extends App {
  case class My(n: Int, k: Int)

  def run(i: Int, j: Int, arr: Array[Array[BigInt]])(implicit my: My) : BigInt = if (i < 0 || j < 0 || i >= my.n || j >= my.k ) 0 else arr(i)(j)

  val (n, k): (Int, Int) = scala.io.StdIn.readLine().split(" ").map(_.toInt)

  implicit def arr2Tupl2(arr: Array[Int]): (Int, Int) = (arr(0), arr(1))
  implicit val myImp = My(n, k)

  var arr = Array.ofDim[BigInt](n, k)
  arr(0)(0) = 1

  for (r <- (1 until n + k - 1); i <- (0 to r); j = r - i; if (i < n && j < k)) {
    arr(i)(j) = run(i - 2, j - 1, arr) + run(i - 1, j - 2, arr) + run(i - 2, j + 1, arr) + run(i + 1, j - 2, arr)
  }
  println(arr(n - 1)(k - 1))
}