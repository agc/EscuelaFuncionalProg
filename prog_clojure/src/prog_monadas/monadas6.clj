; monada estado
; funciones de un parámetro
; valores monádicos (f estado) -> [resultado, estado']
; funciones monadicas (f valor) -> valor monadico

; las funciones monadicas a partir de una valor ordinario producen una funcion
; que hace dos cosas, modificar el estado y calcular el resultado 
; (m-result x ) es un valor monadico

(defmonad estado-m
	[m-result (fn [x] (fn [estado] [x estado] ) )
	 m-bind   (fn  [procesar-estado procesar-valor]  (fn [e] 
	 	            ( let [ [valor e-actualizado]             (procesar-estado e) 
	 	                     procesar-estado-actualizada      (procesar-valor valor)]
	 	                              
	 	                     (procesar-estado-actualizada      e-actualizado))))]
	 ; resultado es valor-monadico osea 
)

; funciones monadicas : a partir de un valor ordinario crean funciones que actúan en el estado

(defn inc-s [x] (fn [estado] [ (inc x ) (conj estado :inc)])) ; el estado es la secuencia de operaciones realizadas
(defn doble-s [x] (fn [estado] [(* 2 x) (conj estado :double)]))
(defn dec-s [x](fn [estado] [(dec x) (conj estado :dec)]))

(defn hacer-cosas [x] (
	domonad estado-m
     [a (inc-s x)
      b (inc-s a)]

      b
	))

(defn do-things [x] (
	domonad state-m 
	[a (doble-s x)
	 b   (dec-s a)
	 c   (dec-s b)]
	 c
	))

(def computo1 (hacer-cosas 7))
(def computo2 (do-things 7))
; computo1 es un valor monadico , es decir actúa en estado
; se le hace actuar en el estado vacio []
; (computo1 [])  

; hacer-cosas   m-bind (inc-s x) (inc-s )

