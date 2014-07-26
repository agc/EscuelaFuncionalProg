(require 'clojure.set)

(defn mitad-doble [n] #{(/ n 2) (* 2 n)}) ; funcion monadica el resultado es un set

(defn inc-int [n] #{(+ 5 n) (+ 10 n)})    ;  funcion monadica el set es el valor monadico




(defmonad mimonada-m
	[m-result (fn m-result-mimonada [x] #{x} )
	 m-bind   (fn m-bind-mimonada [mv f]  (apply clojure.set/union (map f mv))      ) ]
	)

(defn mi-composicion [f1 f2]
	(fn [z]
	(with-monad mimonada-m	
	(domonad 
		[x (f2 z)
		 y  (f1 x)]
		 (+ x y)
	)))	)


(defn otra-composicion [n]
	(domonad mimonada-m
		[x (mitad-doble n)
		 y (inc-int x)]
		 [x y]
		)
	)		




