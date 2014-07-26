(use 'clojure.algo.monads)

(defn -main [] (println ( "Monadas 1" ) ))

; compone usando la monada sequence-m tres funciones
; la funcion monadica transforma un valor entero en una lista de enteros
; el valor monadico es una lista
; no es necesario que tengan el mismo numero de elementos

(defn m-comp [f1 f2 f3]
	(fn [x] (domonad sequence-m 
					[a (f3 x )
					 b (f2 a)
					 c (f1 b)]
					 c )))

(defn f1 [x] ( list  (+ 2 x)))

(defn f2 [x] ( list ( - 1 x) (+ 1 x)))

(defn f3 [x] ( list  ( - 1 x) x (+ 1 x)))

(defn composicion [x] ((m-comp f1 f2 f3) x ))

; int-fn actúa sobre un entero y produce una lista

(defn mi-bind [ns int-fn] (apply concat (map int-fn ns))) 

(def mi-result list)

(defn mi-composicion [f1 f2 f3]
	(fn [x] (let [
			xm (mi-result x      )
			a  (mi-bind   xm f3  )
			b  (mi-bind   a  f2  )
			c  (mi-bind   b  f1  )
		]
		c
		))
	
	) 

(defn composicion2 [x] ((mi-composicion f1 f2 f3) x ))
