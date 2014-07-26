(require 'clojure.set)

(defn mitad-doble [n] #{(/ n 2) (* 2 n)}) ; funcion monadica el resultado es un set

(defn inc-int [n] #{(+ 5 n) (+ 10 n)})    ;  funcion monadica el set es el valor monadico

(defn mi-bind [ns int-fn] (apply clojure.set/union (map int-fn ns))) 

;(def mi-result (fn [x] (into #{} (list x))))

(defn mi-result [x] #{x})

(defn mi-composicion-a [f1 f2 ]
	(fn [z] (mi-bind (f2 z) f1)))

(defn mi-composicion-b [f1 f2 ]
	(fn [z] 
		(mi-bind (f2 z) 
			(fn[x] (mi-bind (f1 x ) 
				(fn [y] (mi-result  [x y])))))))

; esa estructura
; mi-bind
;    fn
;       mi-bind
;          fn
;			mi-result

; se usa para crear un macro, ver el siguiente archivo monads3

(defn mi-composicion-c [f1 f2]
	(fn [z]
	(domonad 
		[x (f2 z)
		 y  (f1 x)]
		 [x y]
	)))			




;f1 se sustituye por equivale a f1
; se debe definir una funcion porque espera una funcion, si se quiere que el resultado sea
; mas complejo


(defn otraf1 [f1] (fn[x] (mi-bind (f1 x ) mi-result)))
(defn otra-masf1 [f1] (fn[x] (mi-bind (f1 x ) (fn [y] (mi-result  y)))))
(defn ver-pares [f1] (fn[x] (mi-bind (f1 x ) (fn [y] (mi-result  [x y])))))