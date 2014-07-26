package proglogica

// Esta monada da forma a la operacion elegir  entre varias (ramas o caminos) alternativas 
// debe extender el trait Logic
// la implementación es la siguiente: una elección es una función
// que 
// toma como argumentos dos funciones
// success continuation esta función indica que se hace con cada alternativa sk
// failure continuation esta función indica que se hace cuando no hay más alternativas fk
// success lo que hacemos es, bien retornar la alternativa, si es una hoja del arbol
// o realizar alguna operación en ella
// posiblemente formando nuevas ramas con raiz en la alternativa

// failure lo que se hace es volver al último punto de ramificación y probar con la 
// siguiente alternativa. Si no hubiera mas alternativas en el ultimo punto retrocedemos otra
// otra vez hasta que se puede tener exito o se han terminado las alternativas

// se está haciendo deep-first search en el arbol con la diferencia que el arbol no es una
// estructura de datos sino que se va creando sobre la marcha
// branch se llama choice point volver atrás backtracking


// Idea: cuando se tenga quee elegir elegir(f_exito,f_fracaso) dará el resultado final de la eleccion tipo R

// la funcion SK calcula una valor a partir de una alternativa (tipo A) pero se le pasa
// como parámetro otra función que se ejecutará en caso de fallo(continuación), para
// ser ejecutada si la rama, eventualmente, colapsa: al llamar a fail o al llamar a filter
// si ninguna rama satisface el predicado. La continuación fallo se llama también para proceder
// con la siguiente alternativa después de haber retornado una hoja ( ver split)

// duda T[A] debe ser concretado type T[A]=   como? donde?
// fail[A] devuelve una elección sin alternativas. Elegir significa invocar fallo
// unit[A](a:A) crea una operación que es elegir una única alternativa
// como no hay mas ramas si ésta falla la operación falla. Se debe disponer de una función fk
// or crea un punto de elección mi idea ha sido t1(sk,t2(sk,fk))
// pero el segundo argumento no es una función sino el resultado de una función por ello
// ()=>t2(sk,fk)
// en apply no es valido sk(f(a),fk) porque a sk se le debe pasar una función


object LogicSFK extends Logic {
	type FK[R]= () => R
	type SK[A,R] = (A,FK[R]) => R 

	trait T[A] {
		def apply[R](sk:SK[A,R],fk:FK[R]):R 
	}

	def fail[A]:T[A] = new T[A] {
		def apply[R](sk:SK[A,R],fk:FK[R])=fk()
	}

	def unit[A](a:A):T[A]=new T[A] {
		def apply[R](sk:SK[A,R],fk:FK[R])=sk(a,fk)
	}

	def or[A](t1:T[A],t2 : =>T[A]):T[A]=new T[A] {
		def apply[R](sk:SK[A,R],fk:FK[R])=t1(sk,()=>t2(sk,fk))
	}

	// cuando hay exito se usa el valor transformado f(a)
	def apply[A,B](t:T[A],f: A => B):T[B]=new T[B] {
		def apply[R](sk:SK[B,R],fk:FK[R])= t({(a,fk)=>sk(f(a),fk)}:SK[A,R],fk)     
	}

	// cada rama se expande f(a) en esa elección de alternativas se le pasan las funciones
	// de exito y error
	def bind[A,B](t:T[A],f:A =>T[B]):T[B]=new T[B] {
		def apply[R](sk:SK[B,R],fk:FK[R])= t({(a,fk)=>f(a)(sk,fk)}:SK[A,R],fk)     
	}

	// tambien compila poniendo 
	// {(a,fk)=>if(p(a)) sk(a,fk) else fk()}:SK[A,R] en la posicion sk2

	def filter[A](t:T[A],p: A => Boolean):T[A]=new T[A] {
		def apply[R](sk:SK[A,R],fk:FK[R])= {

			val sk2:SK[A,R]=(a,fk)=>if(p(a)) sk(a,fk) else fk()
			t(sk2 ,fk)
			}    
	}
 
	// separa una opcion y la devuelve junto con una eleccion entre
	// las restantes

	def split[A](t:T[A]):Option[(A,T[A])]={

		def unsplit(fk:FK[Option[(A,T[A])]]):T[A]=
		fk() match {
			case None => fail
			case Some((a,t))=>or(unit(a),t)
		}


		//fk es ()=>Option[(A,T[A])]
		def sk:SK[A,Option[(A,T[A])]]= {(a,fk)=>Some(a,bind(unit(fk),unsplit))}
		
		// como sk devuelve una opcion el resultado es opcion

		// t es la computacion de elegir como debe resultar en una Opcion
		// la función exito devuelve una Opcion. La función fracaso también, None
		// ¿que hace sk el primer elemento de la opción es un elemento
		// el segundo una eleccion construida con bind
		t(sk,{()=>None})
	}


}