package lectures.part2oop

object AbstractDataTypes extends App {

  // abstract
  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    def eat: Unit = println("crunch crunch")
  }

  // traits
  // can be inherited along with classes
  trait Carnivore {
    def eat(animal: Animal): Unit
    val prefferedMeal: String = "fresh meat"
  }
  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // trait vs abstract classes
  // 1 - traits do not have constructor paramters
  // 2 - multiple traits may be inherited by the same class
  // traits = behavior, abstract class = "thing"



}
