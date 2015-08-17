package demo

/**
 * It's common to rename Java classes in imports for convenience.
 */
import java.util.{List => jList}

/**
 * Support for easy Java getter and setter definition.
 */
import scala.beans.BeanProperty

/**
 * Note that we prefer `JavaConverters` over `JavaConversions` when working from
 * Scala.
 */
import scala.collection.JavaConverters._

/**
 * Note that we use "extends" even though we're implementing an interface.
 */
class JavaInterface1 extends JavaInterface {
  /**
   * Be very careful about semicolon inference when porting Java code.
   */
  val sum = 10
          + 20
          + 30

  /**
   * Syntax is different for varargs, but otherwise it works just fine.
   */
  def foo(x: Int, strings: String*): Unit = {
    strings.foreach(println)
  }

  /**
   * Scala has keywords that Java doesn't, but you can escape them with
   * backticks.
   */
  def `forSome`(a: Int): String = "foo"
}

class JavaAccess1 extends JavaAccess {
  /**
   * Package scoped access is *like* Java's default.
   */
  protected[demo] def secretString = "foo"

  protected def lessSecretString = "bar"

  /**
   * Public is the Scala default.
   */
  def nonsecretString = "qux"
}

class JavaAccess2 extends JavaAccess {
  def secretString = "oof"
  def lessSecretString = "rab"

  /**
   * We can restrict Java access modifiers.
   */
  protected def nonsecretString = "xuq"
}

abstract class ScalaAccess {
  def nonsecretInt: Int
}

class ScalaAccessImpl extends ScalaAccess {
  /**
   * This does not compile; can't restrict Scala access modifier.
   */
  //protected def nonsecretInt: Int = 10
  def nonsecretInt: Int = 10
}

class ScalaPrivate {
  private[this] val x = 10
  private val y: Int = 1000
}

class ScalaFinal {
  val x = 1234
  final val y: Int = 123456
  final val z = 12345

  def getX = x
  def getY = y
  def getZ = z
}

class JavaClass1 extends JavaClass {
  /**
   * We can implement a method with a val.
   */
  val someStrings: jList[String] = Seq("foo", "bar").asJava

  /**
   * Scala doesn't have raw generic types, so we use an existential type.
   */
  protected def someStuff: jList[_] = Seq("foo", "bar").asJava
}


/**
 * Note that we can extend a Java class with a trait.
 */
trait JavaClass2 extends JavaClass {
  protected def someStuff: jList[_] = Nil.asJava
}

/**
 * Static field and methods aren't in scope automatically.
 */
class JavaStatics1 extends JavaStatics {
  def scalaInstanceFoo = JavaStatics.staticFoo
}

/**
 * Not even for Scala objects.
 */
object JavaStatics2 extends JavaStatics {
  def scalaObjectFoo = JavaStatics.staticFoo
}

/**
 * Partial implementation.
 */
trait JavaInterface2 extends JavaInterface {
  def foo(x: Int, strings: String*): Unit = {
    strings.foreach(println)
  }
}

/**
 * Getters and setter will be defined automatically (in addition to the Scala
 * ones).
 */
class JavaBean1(
  @BeanProperty var name: String,
  @BeanProperty val age: Int
) extends JavaBean

/**
 * We can extend a generic type with an upper bound.
 */
class JavaGenerics1 extends JavaGenerics[JavaClass1] {
  def getJavaClass = new JavaClass1
}

/**
 * This was broken in Scala for a long time, but now it works.
 */
class JavaInnerClasses1 extends JavaInnerClasses {
  import JavaInnerClasses.InsideThingy

  protected def getThingy: InsideThingy = new InsideThingy();
}
