val input = io.Source.fromFile("""D:\~Projects\Scala\Contests\Lesson1\olymp.in""")
val out = new java.io.PrintWriter("""D:\~Projects\Scala\Contests\Lesson1\olymp.out""")

val iter = input.getLines()
val n = iter.next().toInt
iter.next().trim.split(" ").map(_.toInt).zip(1 to n).sortWith(_._1 > _._1).foreach(v => out.print("%d ".format(v._2)))

input.close()
out.close()