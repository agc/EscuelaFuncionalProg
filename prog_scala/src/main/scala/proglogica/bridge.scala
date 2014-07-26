package proglogica

trait Bridge{

	val Logic:Logic
	
	import Logic._


	                 	// la conversión implicita pasa a estar disponible. Se puede usar T[A]
						// y las funciones lógicas sin cualificación

	object Person extends Enumeration {
		type Person= Value
		val Alice,Bob,Candace,Dave = Value
		val all = List(Alice,Bob,Candace,Dave)
	}
	import Person._  // usar los identificadores sin calificarlos Person.Alice ...
	
	val times = Map(Alice->5, Bob->10,Candace->20,Dave->25)

	case class State(left:List[Person],lightOnLeft:Boolean,timeRemaining:Int) {
			def right= Person.all.filterNot(left.contains)
		}

		// se usa la función or definida en el trait que de una lista
		// obtiene una T[Person]. La conversión implicita hace que T[Person]
		// entienda los métodos usados en la for-comprehension
	def chooseTwo(list:List[Person]):T[(Person,Person)]= 
			for { p1<- or(list); p2<-or(list); if p1 < p2 } yield(p1,p2)
		

	def next(state:State):T[State] = {

		if (state.lightOnLeft) {

			
		for {
				(p1,p2) <- chooseTwo(state.left)
	 			timeRemaining= state.timeRemaining-math.max(times(p1),times(p2))
				if (timeRemaining>=0)
		}
		yield {
					val left= state.left.filterNot {p => p==p1 || p== p2}
					State(left,false,timeRemaining)
		} 
				

	} else {

		for {
			p <- or(state.right)

			timeRemaining= state.timeRemaining-times(p)

			if (timeRemaining>=0)
		} yield {

			State(p::state.left,true,timeRemaining)

		}

	}

		
	}

	private def tree(path:List[State]):T[List[State]] =

		unit(path) |
		( for {
				state <-next(path.head)
				path <- tree(state::path)
			} yield path 
		)
	 


	def search(n:Int): List[List[State]] = {
		val start = List(State(Person.all,true,60))
		val t = 
		for { path<-tree(start); if path.head.left == Nil}
		yield path

		run(t,n)
		
	}
		
}


