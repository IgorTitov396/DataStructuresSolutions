object Main extends App {
  val input = io.Source.fromFile("ropes.in").getLines()
  val output = new java.io.PrintWriter(new java.io.File("ropes.out" ))

  implicit def arr2tuple(arr: Array[BigInt]): Tuple2[BigInt, BigInt] = {
    (arr(0), arr(1))
  }

  val (n, k): Tuple2[BigInt, BigInt] = input.next().split(" ").map(BigInt(_))
  val arr = input.mkString(" ").split(" ").map(BigInt(_))
  val sum: BigInt = arr.sum
  val maxL: BigInt = sum / k
  def f(index: BigInt): BigInt = {
    val col: BigInt = arr.map(_/index).sum
    if (col < k) 0 else index
  }
  def threeSearch(leftIndex: BigInt = 1, rightIndex: BigInt = maxL): BigInt = {
    val midleIndexLeft: BigInt = leftIndex + (rightIndex - leftIndex + 1) / 3
    val midleIndexRight: BigInt = leftIndex + (rightIndex - leftIndex + 1) * 2 / 3
    if (leftIndex + 3 >= rightIndex) (leftIndex to rightIndex).map(a => f(BigInt(a.toString()))).max
    else if (f(midleIndexLeft) > f(midleIndexRight)) threeSearch(leftIndex, midleIndexRight)
    else threeSearch(midleIndexLeft, rightIndex)
  }

  if (sum < k) output.println(0) else {
    output.println(threeSearch())
  }
  output.close()
}