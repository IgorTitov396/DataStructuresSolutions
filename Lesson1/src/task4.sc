object Main extends App {
  var error = false
  val st = collection.mutable.Stack[Char]()
  io.StdIn.readLine.foreach(
    _ match {
      case '{' => st.push('{')
      case '(' => st.push('(')
      case '[' => st.push('[')
      case ')' => if (st.isEmpty) error = true else {if (st.top != '(') error = true}
        if (!error) st.pop
      case '}' => if (st.isEmpty) error = true else {if (st.top != '{') error = true}
        if (!error) st.pop
      case ']' => if (st.isEmpty) error = true else {if (st.top != '[') error = true}
        if (!error) st.pop
      case _ => ()
    }
  )
  System.out.print(if (error || st.size != 0) "NO" else "YES")
}