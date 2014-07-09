object Bridge0 {


	object Person extends Enumeration {
		type Person= Value
		val Alice,Bob,Candace,Dave = Value
		val all = List(Alice,Bob,Candace,Dave)

		import Person._  // usar los identificadores sin calificarlos Person.Alice ...
		
		val times = Map(Alice->5, Bob->10,Candace->20,Dave->25)

		case class State(left:List[Person],lightOnLeft:Boolean,timeRemaining:Int) {
			def right= Person.all.filterNot(left.contains)
		}

		// todas las parejas posibles  a partir de la lista 
		// el orden se obtiene a partir de la enumeración

		def chooseTwo(list:List[Person]):List[(Person,Person)]= {
			val init: List[(Person,Person)]= Nil // hay que especificar el tipo

			list.foldLeft(init) {
				(pares,p1) => list.foldLeft(pares) {
					(pares, p2)=> if (p1<p2) (p1,p2)::pares else pares

				}
			}

		}

		// a partir de una estado obtiene todos los estados
		// que se pueden derivar de él, estados sucesores
		// heurística
		// si la luz está en la izquierda movemos dos personas a la
		// derecha
		// si la luz está en la derecha movemos una persona a la
		// izquierda

		// la limitación de los movimientos posibles está en
		// el tiempo disponible



		// Construye una lista de estados
		// En cada Estado de la lista se ha modificado state
		// con un movimiento a la otra orilla
		// son todos los estados que pueden seguirse del actual

		def next(state:State):List[State] = {

		if (state.lightOnLeft) {

			val init:List[State]=Nil
			// forma pares en las personas de la orilla izquierda
			// recorre la lista y con cada par que  la condicion del tiempo restantes
			// forma un estado en el que en la orilla izquierda se habrá eliminado ese par
			// y la luz habrá pasado a la derecha
			chooseTwo(state.left).foldLeft(init) { // (List,par)-> List  el resultado final es List

	 			case (states,(p1,p2))=> 
				val timeRemaining= state.timeRemaining-math.max(times(p1),times(p2))

				if (timeRemaining>=0) {
					val left= state.left.filterNot {p => p==p1 || p== p2}

					State(left,false,timeRemaining)::states
				} 
				else 
					states
				
			}

		} else {

			val init:List[State]=Nil
			//val right= Person.all.filterNot(state.left.contains) // es la forma de ver quien esta en la derecha
			val right= state.right
			right.foldLeft(init) {
				(states,p)=> 
				
				val timeRemaining= state.timeRemaining-times(p)

				if (timeRemaining>=0) {
					
					State(p::state.left,true,timeRemaining)::states
				} 
				else 
					states

			}

			
		}
		
	}
	def tree(path:List[State]):List[List[State]] = next(path.head).
			map(siguiente=>tree(siguiente::path)).
				foldLeft(List(path)) { _ ++ _ }


	def search: List[List[State]] = {
		val start = List(State(Person.all,true,60))

		tree(start).filter { _.head.left == Nil}
	}

	}
}