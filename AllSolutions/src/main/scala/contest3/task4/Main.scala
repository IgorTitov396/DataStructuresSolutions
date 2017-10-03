package contest3.task4

import java.math.BigInteger
import java.util

import scala.collection.mutable

object Main extends App {

  class Inf
  object Inf {
    def apply(): Inf = new Inf()
  }

  class BInt(private var a: BigInt = 0, private var i: Inf = null) extends Ordering[BInt] {
    def +(that: BInt): BInt = if (this.i == null && that.i == null) BInt(this.a + that.a) else BInt.inf

    def notInfine = i == null
    def isInfine = i != null
    override def toString: String = if (this.i == null) this.a.toString() else "inf"
    override def compare(x: BInt, y: BInt): Int =
      (x, y) match {
        case (a, b) if (a < b) => -1
        case (a, b) if (b < a) => 1
        case _ => 0
      }
    def <(that: BInt): Boolean = if (this.i != null) false else {
      if (that.i != null) true else this.a < that.a
    }
    def get: BigInt = if (this.i == null) this.a else -1

    def len = if (i == null) BInt(this.a) else  BInt.inf
  }

  object BInt {
    def apply(v :BigInt): BInt = new BInt(a = v)
    def apply(v: Inf): BInt = new BInt(i = v)
    def inf: BInt = BInt.apply(Inf())
  }


  def minOf3(v1: BInt, v2: BInt, v3: BInt): BInt = {
    var answ = BInt.inf
    if (v1.notInfine && v1 < answ) answ = v1
    if (v2.notInfine && v2 < answ) answ = v2
    if (v3.notInfine && v3 < answ) answ = v3
    answ
  }

  def minIdex(arr: Array[BInt], i1: Int, i2: Int, i3: Int ): (Int, Int) = {
    var minIndex = (i1, 1)
    if (arr(i2) < arr(minIndex._1)) minIndex = (i2, 2)
    if (arr(i3) < arr(minIndex._1)) minIndex = (i3, 3)
    return minIndex
  }

  val st = mutable.Stack[BigInt]()
  val n = scala.io.StdIn.readLine().split(" ").map(_.toInt).apply(0)

  def run(i: Int, arr: Array[BInt]): BInt = {
    minOf3(arr(i - 1) + BInt(1), if ((i + 1) % 2 == 0) arr(i / 2) + BInt(1) else BInt.inf, if ((i + 1) % 3 == 0) arr(i / 3) + BInt(1) else BInt.inf)
  }

  var arr = Array.ofDim[BInt](n)
  arr(0) = BInt(0)
  for (i <- (1 until n)) arr(i) = run(i, arr)
  println(arr(n - 1).get)
  var iter: Int = n - 1
  var answ: Int = n
  var buf = 0
  st.push(answ)
  while (iter != 0) {
    val buf2 = minIdex(arr, iter - 1, if ((iter + 1) % 2 == 0) (iter + 1) / 2 - 1 else n - 1, if ((iter + 1) % 3 == 0) (iter + 1) / 3 - 1 else n - 1)
    iter = buf2._1
    buf = buf2._2
    buf match {
      case 1 => answ = answ - 1
      case 2 => answ = answ / 2
      case 3 => answ = answ / 3
    }
    st.push(answ)
  }
  while (!st.isEmpty) print(st.pop() + " ")
}