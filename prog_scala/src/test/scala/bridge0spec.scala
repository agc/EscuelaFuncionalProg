
import org.specs2.mutable._
object Bridge0Spec extends Specification {

	import Bridge0.Person._

	"Bridge0 " should {
		
		"Permitir el acceso a la lista de Personas " in {
			
			Bridge0.Person.all must contain (Alice,Bob)
		}

		"Crear todos los pares posibles de personas" in {

			chooseTwo(all).size mustEqual 6

		}

		"Calcular los estados siguientes " in {
			// Pasan Alice Bob y les cuesta 10 minutos
			next(State(all,true,50)) must contain( State(List(Candace, Dave),false,40))

			next(State(List(Candace,Dave),false,50)) must contain (State(List(Bob, Candace, Dave),true,40))
		}

		" buscar paths que resuelven el problema " in {
			search must contain ( 1, 2 , 3)
		}
		

	} 
}