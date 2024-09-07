package lectures.part2oop

object Exceptions extends App{

  val x: String = null
//  println(x.length)
  // crashes with NPE

  // 1. throwing exceptions
  // val aWeirdValue: String = throw new NullPointerException


  // throwable classes extend the Throwable class
  // Exception and Error (StackOverflow Error) are the major Throwable subtypes

  // 2. How to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    // code that might throw
    getInt(false)
  } catch {
    case e: RuntimeException => 43
  } finally { // optional
    // code that will get executed NO MATTER WHAT
    // does not influence return type of expression
    // use only for side effects (logging)
    println("finally")
  }

  println(potentialFail)

  // 3 . how to define your own Exceptions
  class MyException extends Exception
  val exception = new MyException



  // Crash Program with OutOfMemoryError

  // val array = Array.ofDim(Int.MaxValue)


  // Crash with SOError

  def infinite: Int = 1 + infinite
  // val noLimit = infinite

  /*
   Pocket Calculator
   - add(x,y)
   - subtract(x,y)
   - multiply(x,y)
   - divide(x,y)

   Throw
    - OverflowException if add(x,y) exceeds Int.MAX_VALUE
    - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
    - MathCalculationException for division by 0
   */
  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")
  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int) = {
      val result = x - y

      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
    }

    def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

}
