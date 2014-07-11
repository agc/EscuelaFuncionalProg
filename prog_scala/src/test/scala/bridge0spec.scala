
import org.specs2.mutable._
object BridgesSpec extends Specification {

	

	"Bridge 0" should {

		import proglogica.Bridge0._
		import proglogica.Bridge0.Person._
		
		"Permitir el acceso a la lista de Personas " in {
			
			all must contain (Alice,Bob)
		}

		"Crear todos los pares posibles de personas" in {

			chooseTwo(all).size mustEqual 6

		}

		"Calcular los estados siguientes " in {
			// Pasan Alice Bob y les cuesta 10 minutos
			next(State(all,true,50)) must contain( State(List(Candace, Dave),false,40))

			next(State(List(Candace,Dave),false,50)) must contain (State(List(Bob, Candace, Dave),true,40))
		}

		"Buscar paths que resuelven el problema " in {
			search.size mustEqual 2 
		}
		

	} 

	"Bridge 1" should {

		import proglogica.Bridge1._
		import proglogica.Bridge1.Person._
		
		"Permitir el acceso a la lista de Personas " in {
			
			all must contain (Alice,Bob)
		}

		"Crear todos los pares posibles de personas" in {

			chooseTwo(all).size mustEqual 6

		}

		"Calcular los estados siguientes " in {
			// Pasan Alice Bob y les cuesta 10 minutos
			next(State(all,true,50)) must contain( State(List(Candace, Dave),false,40))

			next(State(List(Candace,Dave),false,50)) must contain (State(List(Bob, Candace, Dave),true,40))
		}

		"Buscar paths que resuelven el problema " in {
			search.size mustEqual 2 
		}
		

	} 
}