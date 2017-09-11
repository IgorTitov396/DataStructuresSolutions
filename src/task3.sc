val input = io.Source.fromFile("""D:\~Projects\Scala\Contests\Lesson1\trucks.in""")
val out = new java.io.PrintWriter("""D:\~Projects\Scala\Contests\Lesson1\trucks.out""")

val arr = input.mkString.trim.split(" ").map(_.toInt)

def calc(size: Int, cars: Int): Int = {
  if (size <= cars) 1 else calc(size / 2, cars) + calc(size - (size/2), cars)
}

out.print(calc(arr(0), arr(1)))

input.close()
out.close()