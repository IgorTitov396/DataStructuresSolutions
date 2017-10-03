var count = 0
def merge(left: Array[Int], right: Array[Int]): Array[Int] = {
  val answ: Array[Int] = new Array[Int](left.length + right.length)
  var (i, j) = (0, 0)
  while (i < left.length && j < right.length) {
    if (left(i) < right(j)) {
      answ(i + j) = left(i)
      i += 1
    } else {
      answ(i + j) = right(j)
      j += 1
      count += 1
    }
  }
  if (i == left.length) (j until right.length).foreach(ind => answ(ind + i) = right(ind)) else {
    (i until left.length).foreach(ind => answ(ind + j) = left(ind))
    count += left.length - i
  }
  answ
}

def mergeSort(array: Array[Int], leftIndex: Int, rightIndex: Int): Array[Int] = {
  val mid = leftIndex + (rightIndex - leftIndex) / 2
  if (rightIndex >= leftIndex + 1) array.sortWith(_ < _) else merge(mergeSort(array, leftIndex, mid), mergeSort(array, mid + 1, rightIndex))
}

val a = "1 2 4 5 6".split(" ").map(_.toInt)
val b = "1 3 2 5 1 7 1".split(" ").map(_.toInt)
merge(b, a)
b.length - 1
mergeSort(b, 0, b.length - 1)



def index(leftIndex: Int, rightIndex: Int): (Int, Int, Int, Int) = {
  val midleIndexLeft = leftIndex + (rightIndex - leftIndex + 1) / 3
  val midleIndexRight = leftIndex + (rightIndex - leftIndex + 1) * 2 / 3
  (leftIndex, midleIndexLeft, midleIndexRight, rightIndex)
}

val k = 15
for (i <- 1 to k; j <- 1 to k) {
  val (l, lm, rm, r) = index(i, j)
  if (l <= r) println(s"$l $lm $rm $r")
}