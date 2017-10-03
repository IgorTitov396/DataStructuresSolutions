package task5

object Main extends App {
  val input = io.Source.fromFile("ropes.in").getLines()
  val output = new java.io.PrintWriter(new java.io.File("ropes.out" ))

  implicit def arr2tuple(arr: Array[Long]): Tuple2[Long, Long] = {
    (arr(0), arr(1))
  }

  val (n, k): Tuple2[Long, Long] = input.next().split(" ").map(_.toLong)
  val arr = input.mkString(" ").split(" ").map(_.toLong)
  val sum: Long = arr.sum
  val maxL: Long = sum / k
  def f(index: Long): Long = {
    val col: Long = arr.map(_/index).sum
    if (col < k) 0 else index
  }
  def threeSearch(leftIndex: Long = 1, rightIndex: Long = maxL): Long = {
    val midleIndexLeft: Long = leftIndex + (rightIndex - leftIndex + 1) / 3
    val midleIndexRight: Long = leftIndex + (rightIndex - leftIndex + 1) * 2 / 3
    if (leftIndex + 3 >= rightIndex) (leftIndex to rightIndex).map(f(_)).max
    else if (f(midleIndexLeft) > f(midleIndexRight)) threeSearch(leftIndex, midleIndexRight)
    else threeSearch(midleIndexLeft, rightIndex)
  }

  if (sum < k) output.println(0) else {
    output.println(threeSearch())
  }
  output.close()
}