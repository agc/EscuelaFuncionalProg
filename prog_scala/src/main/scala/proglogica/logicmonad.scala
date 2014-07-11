package proglogica

/*
	Un objeto que implementa el trait representa una elección entre varias alternativas
	fail devuelve una instancia que representa una elección entre ninguna alternativa
	unit elección con una única alternativa
	or(t1,t2) es una instancia que representa la elección entre las alternativas de t1 junto con las de t2
	apply es una elección entre los resultados de aplicar a las alternativas la función f
	bind es igual pero la función asocia a cada alternativa varias alternativas: función multivaluada
	filter selecciona las alternativas que cumplen la función boolean , es una elección entre ellas
	split retorna la primera alternativa junto con una elección entre las restantes

	or aplicado a lista construye una elección entre los elementos de la lista

	run retorna una lista con las n primeras alternativas disponibles. Así es como se obtienen
	las respuestas a partir de un T[A]

	se define una clase case que implementa las funciones necesarias para usar for comprehension

	una conversión implicita dese T[A] a esta clase permitirá  usar directamente for-comprehension
	en T[A]

	t.metodo  se convierte en Syntax(t).metodo()=InstanciaT.otrometodo(t)

*/
trait Logic { L=>

	type T[A]

	def fail[A]:T[A]	
	def unit[A](a:A):T[A]
	def or[A](t1:T[A],t2 : =>T[A]):T[A]
	def apply[A,B](t:T[A],f: A => B):T[B]
	def bind[A,B](t:T[A],f:A =>T[B]):T[B]
	def filter[A](t:T[A],p: A => Boolean):T[A]
	def split[A](t:T[A]):Option[(A,T[A])]

	def or[A](as:List[A]):T[A]= as.foldRight(fail[A])((a,t)=>or(unit(a),t))

	def run[A](t:T[A],n:Int):List[A]=
		if (n<=0) Nil else
		split(t) match {
			case None => Nil
			case Some((a,t)) => a :: run(t,n-1)
		}

	case class Syntax[A](t:T[A]) {

		def map[B](f : A=>B):T[B]= L.apply(t,f)
		def filter(p : A => Boolean):T[A]=L.filter(t,p)
		def flatMap[B](f:A=>T[B]):T[B]=L.bind(t,f)
		def withFilter(p: A=> Boolean):T[A]=L.filter(t,p)

		def |(t2: =>T[A]):T[A]= L.or(t,t2)


	}

	implicit def syntax[A](t:T[A])= Syntax(t)


}