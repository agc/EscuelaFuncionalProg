(require 'clojure.set)

(defn mitad-doble [n] #{(/ n 2) (* 2 n)}) ; funcion monadica el resultado es un set

(defn inc-int [n] #{(+ 5 n) (+ 10 n)})    ;  funcion monadica el set es el valor monadico

(defn mi-bind [ns int-fn] (apply clojure.set/union (map int-fn ns))) 

;(def mi-result (fn [x] (into #{} (list x))))

(defn mi-result [x] #{x})

(defn mi-composicion [f1 f2 ]         ;primero f2 y a su resultado f1
	(fn [x] (let [
			xm (mi-result x      )
			a  (mi-bind   xm f2  )
			b  (mi-bind   a  f1  )
			
		]
		b
		))
	
	) 

(defn composicion2 [x] ((mi-composicion inc-int mitad-doble) x ))
