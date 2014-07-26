; valores monadicos funciones sin parámetros

; funciones monadicas a cada x asocian un vm es decir una función sin parámetros
; (vm) es un número
; ejemplo

(defn generador-funcion [x] (fn [] (inc x))) ; a 3 le asocia la función que devuelve 4

;(defn m-result [x] (fn [] x)) ; transforma x en la función que devuelve x

; (m-result 5) es una función, se ejecuta con ((m-result 5))  resultado es 5


; vm es función (vm) es un número (f numero) es un valor monadico 
;(defn m-bind [vm f] (f (vm)))  ;(vm) es un numero f actuando en él es un valor monadico : 
; función

(defmonad monadafuncion-m
	[m-result (fn [x] (fn [] x ) )
	 m-bind   (fn  [vm f]    (f (vm)))        ]
	)

(defn transformar-funcion [f1]
	(fn [x]
	(with-monad monadafuncion-m
	(domonad [y (f1 x)]
		 y					;y es un valor monadico, una funcion
	)))	)

; (((transformar-funcion generador-funcion) 3))
