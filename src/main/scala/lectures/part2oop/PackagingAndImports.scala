package lectures.part2oop

import playground.{PrinceCharming, Cinderella => Princess}

import java.sql
import java.util.Date
import java.sql.{Date => sqlDate}

object PackagingAndImports extends App {
  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  // import the package
  val princess = new Princess  // playground.Cinderella = fully qualified name

  // packages are in hierarchy
  // matching folder structure

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  // 2. Use FQ names
  val d = new Date
  val sqlDate = new sqlDate(2018, 5, 4)

  // default imports
  // java.lang = String, Object, Exception
  // scala - Int, Nothing, Function
}
