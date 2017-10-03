val k = 10
def func(v1: Int, v2: Int): Int = v1 * v2

val (leftIndex, midleIndexLeft, midleIndexRight, rightIndex) = (1, 2, 3, 4)
Array[Int](leftIndex, midleIndexLeft, midleIndexRight, rightIndex).distinct.zip(Array[Int](leftIndex, midleIndexLeft, midleIndexRight, rightIndex).distinct.drop(1)).map(a => func(a._1, a._2)).min

for (w <- 1 to k; h <- 1 to k; n <- 1 to k) {
  def f(index: Int): Int = {
    math.max(w*(math.ceil(n/index.toDouble)).toInt, h*index)
  }
  def binSearch(leftIndex: Int = 1, rightIndex: Int = n): Int = {
    val midleIndexLeft = (leftIndex + rightIndex - 1) / 3 + 1
    val midleIndexRight = (leftIndex + rightIndex - 1) * 2 / 3 + 1
    if (f(midleIndexLeft) < f(midleIndexRight)) {
      if (leftIndex > midleIndexLeft - 1) f(leftIndex)
      else if (midleIndexLeft > midleIndexRight - 1) f(midleIndexLeft)
      else math.min(binSearch(leftIndex, midleIndexLeft - 1), binSearch(midleIndexLeft, midleIndexRight - 1))
    } else {
      if (midleIndexLeft + 1 > midleIndexRight) f(midleIndexRight)
      else if (midleIndexRight + 1 > rightIndex) f(rightIndex)
      else math.min(binSearch(midleIndexLeft + 1, midleIndexRight), binSearch(midleIndexRight + 1, rightIndex))
    }
  }

  var curN = 0
  var min = Int.MaxValue
  var delta = 0
  (1 to n).foreach(i => {
    min = math.min(math.max(w*(math.ceil(n/i.toDouble)).toInt, h*i), min)
  })

  val min1 = binSearch()
  val min2 = min
  val isTrue = (min1 == min2)
  println(s"%isTrue %w %h %n")
}
val (n, index) = (1, 1000000000)
(math.ceil(n/index.toDouble)).toInt

