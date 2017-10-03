import java.util.Calendar


object Test extends App {
  val k = 1000
  var start = System.currentTimeMillis()
  for (w <- 1 to 1000; h <- 1 to 1000; n <- 1 to 100) {
    def threeSearch(leftIndex: Int = 1, rightIndex: Int = n): Int = {
      def f(index: Int): Int = {
        math.max(w * (math.ceil(n.toDouble / index.toDouble)).toInt, h * index)
      }
      val midleIndexLeft = leftIndex + (rightIndex - leftIndex + 1) / 3
      val midleIndexRight = leftIndex + (rightIndex - leftIndex + 1) * 2 / 3
      if (leftIndex + 3 >= rightIndex) { (leftIndex to rightIndex).map(f(_)).distinct.min } else {
        if (f(midleIndexLeft) > f(midleIndexRight)) threeSearch(midleIndexLeft, rightIndex) else {
          if (f(midleIndexRight) > f(midleIndexLeft)) threeSearch(leftIndex, midleIndexRight) else {
            Array[Int](leftIndex, midleIndexLeft, midleIndexRight, rightIndex).distinct.zip(Array[Int](leftIndex, midleIndexLeft, midleIndexRight, rightIndex).distinct.drop(1)).map(a => threeSearch(a._1, a._2)).min
          }
        }
      }
    }
  //}
  var finish = System.currentTimeMillis()
  //println(s"ThreeSearchTime: " + (finish - start))
  start = System.currentTimeMillis()
  //for (w <- 1 to k; h <- 1 to k; n <- 1 to k) {
    def f(index: Int): Int = {
      math.max(w * (math.ceil(n / index.toDouble)).toInt, h * index)
    }
    /*
    var curN = 0
    var delta = 0
    var min = f(1)
    (1 to n).foreach(i => {
      val buf = math.max(w*(math.ceil(n/i.toDouble)).toInt, h*i)
      min = math.min(buf, min)
      //print(s"$buf ")
    })
    */
    def isContains(index: Int): Boolean = {
      (index / h) * (index / w) < n
    }
    def calculate(leftIndex: Int, rightIndex: Int): Int = {
      var left = leftIndex
      var right = rightIndex
      while (left + 1 < right) {
        var mid = (left + right) / 2
        if (isContains(mid)) left = mid else right = mid
      }
      right
    }
    val min1 = threeSearch()
    val min2 = calculate(1, Array[Int](n * w, n * h).max)
    val isTrue = (min1 == min2)
    if (!isTrue) println(s"$isTrue $w $h $n Answer: $min1 $min2")

  }
  //finish = System.currentTimeMillis()
  //println(s"Perebor: " + (finish - start))
}

object Test2 extends App {
  val n = 1000000000
  for (index <- 1 to n) {
    val v1 = math.ceil(n.toDouble / index.toDouble).toInt
    val v2 = math.ceil(n.toDouble / index).toInt
    if (v1 != v2) println(s"False $v1 $v2")
  }
}