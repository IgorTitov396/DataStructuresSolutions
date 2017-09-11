object Main extends App {

  import java.io.PrintWriter

  val input = io.Source.fromFile("""min.in""").getLines()
  val out = new PrintWriter("""min.out""")

  val params = input.next().split(" ").map(_.toInt)
  val (n, k) = (params(0), params(1))
  val arr = input.next.split(" ").map(_.toInt)

  class MyQueueWIthMin[T](min: (T, T) => T) {
    override def toString: String = left.reverse.mkString(" ") + " " + right.reverse.mkString(" ")

    private[this] var s = 0

    def getMin(): T = {
      if (!left.isEmpty && !right.isEmpty) min(left.top._2, right.top._2)
      else if (left.isEmpty) right.top._2
      else left.top._2
    }

    def size(): Int = s

    val right = collection.mutable.Stack[(T, T)]()
    val left = collection.mutable.Stack[(T, T)]()

    //def pushFront(v: T) {left.push((v, min(v, left.top._2))); s += 1}
    def enqueue(v: T) {
      if (right.isEmpty) right.push((v, v)) else right.push((v, min(v, right.top._2))); s += 1
    }

    def dequeue() = {
      if (left.isEmpty) relocate()
      s -= 1
      left.pop()
    }

    def popBack() = {
      if (right.isEmpty) relocate()
      s -= 1
      right.pop()
    }

    private[this] def relocate() {
      if (right.isEmpty) {
        while (!left.isEmpty) {
          val buf = left.pop()
          if (right.isEmpty) right.push((buf._1, buf._1)) else right.push((buf._1, min(buf._1, right.top._2)))
        }
      } else if (left.isEmpty) {
        while (!right.isEmpty) {
          val buf = right.pop()
          if (left.isEmpty) left.push((buf._1, buf._1)) else left.push((buf._1, min(buf._1, left.top._2)))
        }
      }
    }

    def isEmpty = if (size == 0) true else false
  }

  object MyQueueWIthMin {
    def apply[T](min: (T, T) => T): MyQueueWIthMin[T] = new MyQueueWIthMin[T](min)
  }

  def min(a: Int, b: Int): Int = if (a < b) a else b

  val q = MyQueueWIthMin[Int](min)

  (0 until k).foreach(a => q.enqueue(arr(a)))

  out.println("%d ".format(q.getMin()))
  (k until n).foreach(a => out.println("%d ".format({
    q.enqueue(arr(a)); q.dequeue(); q.getMin()
  })))

  out.close
}
