package interpreter

object Main extends App {
  import java.io.{BufferedReader, InputStreamReader}
  import Lisp._
  
  
  val in = new BufferedReader(new InputStreamReader(System.in))
  // TODO: Insert code for the REPL
  var s = ""
  while(!s.equals("quit")) {
    print("lisp> ")
    s = in.readLine()
    println(lisp2string(evaluate(s)))
  }
}

object LispCode {
  // TODO: implement the function `reverse` in Lisp.
  // From a list (a, b, c, d) it should compute (d, c, b, a)
  // Write it as a String, and test it in your REPL
  val reverse = """
  def (reverse L) (
    def (loop ls acc) (
      if (null? ls) acc
      (loop (cdr ls) (cons (car ls) acc))
    )
    (loop L nil)
  )
  """

  // TODO: implement the function `differences` in Lisp.
  // From a list (a, b, c, d ...) it should compute (a, b-a, c-b, d-c ...)
  // You might find useful to define an inner loop def
  val differences = """
  def (differences L) (
    def (loop ls acc) (
      if (null? (cdr ls)) (reverse acc)
      (loop (cdr ls) (cons (- (car (cdr ls)) (car ls)) acc))
    )
    (loop (cons 0 L) nil)
  )
  """
  val rebuildList = """
  def (rebuildList L) (
    if(null? L) nil
    (
    if (null? (cdr L)) L
    (cons (car L) (rebuildList (cons (+ (car L) (car (cdr L))) (cdr (cdr L)))))
    )
  )
  """

  val withDifferences: String => String =
    (code: String) => "(" + reverse + " (" + differences + " (" + rebuildList + " " + code + ")))"
    
    
    /*
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     * ****************************************************************************************
     * ****************************************************************************************
     * TO BE REMOVED !! NOT PART OF The ASSIGNEMENT
     * ****************************************************************************************
     * ****************************************************************************************
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     */
  //Added value for comprehension of Lisp
    /*
     * Find the last elem of the list
     */
  val mylast = """
    def (mylast L) (
      if (null? L) nil
      (
      if (null? (cdr L)) L
      (mylast (cdr L))
      )
    )
    """
  
  val lastElem: String => String = 
    (code : String) => "(" + mylast + " " + code + ")"
    
  /*
   * Find the kth elem of the list
   */
  
  val kthelem = """
    def (kthelem L k) (
      if (> 1 ? k) car L
      (kthelem (cdr L) (- k 1))
    )
    """
  
  val kth: String => String = 
    (code : String) => "(" + kthelem + " " + code + ")"
}
