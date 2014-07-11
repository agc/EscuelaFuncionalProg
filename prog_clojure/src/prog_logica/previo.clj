(ns prog-logica.previo)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hola, Mundo!"))

(defn bar [] (println '("Hola" "Mundo")))

(defn -mainx [] (foo "Primer ejemplo "))

(def -main bar )
