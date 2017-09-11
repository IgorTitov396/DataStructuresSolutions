
val input = io.Source.fromFile("""D:\~Projects\Scala\Contests\Lesson1\queue.in""").getLines().drop(1)
val out = new java.io.PrintWriter("D:\\~Projects\\Scala\\Contests\\Lesson1\\queue.out")

class DeqWithMin(min: (Int, Int) => Int) {
  override def toString: String = left.reverse.mkString(" ") + " " + right.reverse.mkString(" ")
  private[this] var s = 0
  private var minV: Int = Int.MaxValue
  def minVal = minV
  def size(): Int = s
  val right = collection.mutable.Stack[(Int, Int)]()
  val left = collection.mutable.Stack[(Int, Int)]()
  def pushFront(v: Int) {minV = min(minV, v); left.push((v, minV)); s += 1}
  def pushBack(v: Int) {minV = min(minV, v); right.push((v, minV)); s += 1}
  def popFront()= {
    if (left.isEmpty) relocate()
    s -= 1
    minV = min(right.top._2, left.top._2)
    left.pop()
    minV = {
      if (right.isEmpty && left.isEmpty) Int.MaxValue else
      if (right.isEmpty) left.top._2 else
      if (left.isEmpty) right.top._2 else min(right.top._2, left.top._2)
    }
  }
  def popBack() = {
    if (right.isEmpty) relocate()
    s -= 1
    right.pop()
    minV = {
      if (right.isEmpty && left.isEmpty) Int.MaxValue else
        if (right.isEmpty) left.top._2 else
          if (left.isEmpty) right.top._2 else min(right.top._2, left.top._2)
    }
  }
  private[this] def relocate() {
    if (right.isEmpty) {
      while (!left.isEmpty) {
        right.push(left.pop)
      }
    } else if (left.isEmpty) {
      while (!right.isEmpty) {
        left.push(right.pop)
      }
    }
  }
}

object DeqWithMin {
  def apply(min: (Int, Int) => Int): DeqWithMin = new DeqWithMin(min)
}

def min(a: Int, b: Int) = {
  if (a > b) b else a
}
val a = DeqWithMin(min)

a.pushBack(1)
a.pushBack(2)
a.minVal
a.pushBack(3)
a.pushBack(0)
a.minVal
a.popFront()