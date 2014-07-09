(ns prog-logica.ejemplos1

  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic]))

(defn paso1 [] (run 1 [q]))

(defn paso4 [] (run 2 [q] (conde [(== q 3)] [(== q 5)])))

(defn -main [] (println (paso1 ) ))
