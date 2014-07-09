//import org.specs._
import org.specs2.mutable._
object ArithmeticSpec extends Specification {
"Arithmetic" should {
"add two numbers" in {
1 + 1 mustEqual 2
}
"add three numbers" in {
1 + 1 + 1 mustEqual 3
}
}
}
 
//import org.specs._
import org.specs2.mutable._
object ArithmeticSpec2 extends Specification {
"Arithmetic2" should {
"add" in {
"two numbers" in {
1 + 1 mustEqual 2
}
"three numbers" in {
1 + 1 + 1 mustEqual 3
}
}
}
}
 
object ExecSpec extends Specification {
"Mutations are isolated" should {
var x = 0
"x equals 1 if we set it." in {
x = 1
x mustEqual 1
}
var y = 0
"y is the default value if we don't change it" in {
y mustEqual 0
}
}
}
 
import org.specs2.specification._
object BeforeAfterSpec extends Specification with BeforeExample with AfterExample {
def before = () => println("reset the system")
def after = () => println("clean things up")
"my system" should {
// doBefore { resetTheSystem() }
"mess up the system" in {
println("mess up the system...")
1 mustEqual 1
}
"and again" in {
println("and again...")
1 mustEqual 1
}
// doAfter { cleanThingsUp() }
}
}
 
object MatcherSpec extends Specification {

"mathers" should {
	"be available" in {
		1 mustEqual 1
		"a" mustEqual "a"
	}
	
	"be available for elements in a sequence" in {
// http://etorreborre.github.com/specs2/guide/org.specs2.guide.Matchers.html#Matchers+guide
		val numbers = List(1, 2, 3)
		numbers must contain(1)
		numbers must not contain(4)
		// numbers must containAll(List(1, 2, 3))
		numbers must contain(1, 2, 3)
		// numbers must containInOrder(List(1, 2, 3))
		numbers must contain(1, 2, 3).inOrder
		//List(1, List(2, 3, List(4)), 5) must containTheSameElementsAs(List(5, List(List(4), 2, 3), 1))

		// fallo List(1, List(2, 3, List(4)), 5) must contain ( exactly( List(5, List(List(4), 2, 3), 1):_*))

		// error List(1, List(2, 3, List(4)), 5) must contain ( List(5, List(List(4), 2, 3), 1))
	}
}

"map" should {
"be available" in {
val (k,v) = ('kkk, 'vvv)
val map = Map(k -> v)
map must haveKey(k)
// map must notHaveKey(k)
map must not(haveKey('kkkk))
map must haveValue(v)
// map must notHaveValue(v)
map must not(haveValue('vvvv))
}
}
"numbers" should {
"be available" in {
2 must beGreaterThan(1)
2 must beGreaterThanOrEqualTo(2)
1 must beLessThan(2)
1 must beLessThanOrEqualTo(1)
1 must beCloseTo(1, 1)
}
}
"options" should {
"be available" in {
None must beNone
Some(123) must beSome[Int]
// Some("aaa") must beSomething
Some("foo") must beSome("foo")
}
}
"throwA" should {
"be available" in {
def a: Int = { throw new Exception("bad thing") }
a must throwA[Exception]
a must throwA(new Exception("bad thing"))
}
}
}
 
import org.specs2.matcher._
object OwnMatcherSpec extends Specification {
"A matcher" should {
"be created as a method" in {
// http://etorreborre.github.com/specs2/guide/org.specs2.guide.Matchers.html#Matchers+guide
// Matcher creation
def allBeGreaterThan2: Matcher[Seq[Int]] = be_>=(2).forall
Seq(3,4,5) must allBeGreaterThan2
}
}
}
 
import org.specs2.matcher._
import org.specs2.mock.Mockito
abstract class Foo[T] {
def get(i: Int): T
}
object MockExampleSpec extends Specification with Mockito {
"mock" should {
"be available" in {
val m = mock[Foo[String]]
m.get(0) returns "one"
m.get(0) mustEqual "one"
there was one(m).get(0)
there was no(m).get(1)
}
}
}
 
/* NOT WORK
import org.mockito.Mockito._
object SpiesSpec extends Specification with Mockito {
"spy" should {
"be available" in {
val list = new collection.mutable.LinkedList[String]
val spiedList = spy(list)
spiedList.++("one")
there was one(spiedList).++("one")
}
}
}
**/