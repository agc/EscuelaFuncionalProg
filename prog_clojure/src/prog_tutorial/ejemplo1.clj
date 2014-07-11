(ns prog-tutorial.ejemplo1)

; si termina en vocal añade ay
; si termina en consonante quita la inicial y la pone al final antes de añadir ay
(def vocal? (set "aeiou"))

(defn pig-latin [palabra] ; palabra se espera que sea una cadena que se considera secuencia de caracteres
	(let [primera-letra (first palabra)]
		(if (vocal? primera-letra )

			(str palabra "ay")
			(str (subs palabra 1) primera-letra "ay"))))

(defn -main [] (do 
	(println (pig-latin "red"))
	(println (pig-latin "orange"))
	(println (pig-latin "cascabel"))))