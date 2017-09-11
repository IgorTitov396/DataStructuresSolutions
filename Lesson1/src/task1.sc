import java.io.PrintWriter
import java.util

val input = io.Source.fromFile("""D:\\~Projects\\Scala\\Contests\\Lesson1\\postfix.in""")
val out = new PrintWriter("D:\\~Projects\\Scala\\Contests\\Lesson1\\postfix.out")
out.print("")

val st = new util.Stack[Int]()
var isError = 0
try {
  for (i <- input.mkString.trim.split(" ")) {
    i match {
      case "*" => st.push(st.pop() * st.pop())
      case "+" => st.push(st.pop() + st.pop())
      case "-" => st.push(-st.pop() + st.pop())
      case _ => st.push(i.toInt)
    }
  }
} catch {
  case e: Exception => isError = 1
}
if (!st.empty() && isError == 0) out.print("%d".format(st.pop()))

input.close()
out.close()