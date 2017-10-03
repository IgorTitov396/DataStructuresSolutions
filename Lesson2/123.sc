var count = 0

def merge(left: Array[Int], right: Array[Int]): Array[Int] = {
  //var bufS = leftData._2 + rightData._2
  //val (left, right) = (leftData._1, rightData._1)
  val answ: Array[Int] = new Array[Int](left.length + right.length)
  var (i, j) = (0, 0)
  while (i < left.length && j < right.length) {
    if (left(i) <= right(j)) {
      answ(i + j) = left(i)
      i += 1
    } else {
      answ(i + j) = right(j)
      j += 1
      count += left.length - i
    }
  }
  if (i == left.length) (j until right.length).foreach(ind => answ(ind + i) = right(ind)) else {
    (i until left.length).foreach(ind => answ(ind + j) = left(ind))
    //buf += left.length - i
  }
  answ
}

def sort(data: (Array[Int], Int)): (Array[Int], Int) = {
  var col = data._2
  val arr = data._1.toBuffer
  for (i <- 1 until arr.length) {
    var j = i - 1
    while (arr(j) > arr(j + 1)) {
      if (arr(j) > arr(j + 1)) {
        val buf = arr(j)
        arr(j) = arr(j + 1)
        arr(j + 1) = buf
        col += 1
      }
      if (j > 0) j -= 1
    }
  }
  (arr.toArray, col)
}

def mergeSort(array: Array[Int], leftIndex: Int, rightIndex: Int): Array[Int] = {
  val mid = leftIndex + (rightIndex - leftIndex) / 2
  if (leftIndex == rightIndex) {
    Array[Int](array(leftIndex))
  } else {
    merge(mergeSort(array, leftIndex, mid), mergeSort(array, mid + 1, rightIndex))
  }
}

val a = ("999994 999989 999982 999972 999969 999961 999954 999950".split(" ").map(_.toInt), 0)
val b = ("999994 999989 999982 999972 999969 999961 999954 999950".split(" ").map(_.toInt), 0)
val c = "4 3 2 1".split(" ").map(_.toInt)

sort(c, 0)._1

mergeSort(a._1, 0, a._1.length - 1)
count

sort(a)._2

