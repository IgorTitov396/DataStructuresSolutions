
val input = io.Source.fromFile("""D:\~Projects\Scala\Contests\Lesson1\saloon.in""").getLines().drop(1)
val out = new java.io.PrintWriter("D:\\~Projects\\Scala\\Contests\\Lesson1\\saloon.out")

class Client(arr: Array[Int], val pos: Int) {
  val (hour, min, patention) = (arr(0), arr(1), arr(2))
  val time = hour * 60 + min

  override def toString: String = "%d %d %d %d".format(hour, min, patention, pos)
}

object Client {
  def apply(arr: Array[Int], p: Int): Client = new Client(arr, p)
}

var q = scala.collection.mutable.Queue[Client]()

val x = new scala.collection.mutable.PriorityQueue[(Int, Int)]()(Ordering.by(f => -f._1))
//val a = Client(input.getLines().drop(1).next().split(" ").map(_.toInt))
var time = 0
var position = 0
while (input.hasNext) {
  position += 1
  if (q.isEmpty) {
    val buf = Client(input.next().split(" ").map(_.toInt), position)
    q += buf
    time = buf.time + 19
  } else {
    val buf = Client(input.next().split(" ").map(_.toInt), position)
    if (buf.time <= time) {
      if (buf.patention >= q.size) {
        q += buf
      } else {
        x.enqueue(buf.pos -> buf.time)
      }
    }
    if (buf.time > time) {
      while(buf.time > time) {
        if (q.isEmpty) {
          time = buf.time + 19
        } else {
          x.enqueue(q.head.pos -> (time + 1))
          q = q.tail
          if (!q.isEmpty) time += 20
        }
      }
      if (buf.patention >= q.size) {
        q += buf
      } else {
        x.enqueue(buf.pos -> buf.time)
      }
    }
  }
}
while (q.size != 0) {
  x.enqueue(q.head.pos -> (time + 1))
  time += 20
  q = q.tail
}

x.dequeueAll.foreach(a => out.println("%d %d".format(a._2 / 60, a._2 % 60)))

out.close