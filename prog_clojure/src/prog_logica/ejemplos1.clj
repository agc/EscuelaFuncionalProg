(ns prog-logica.ejemplos1

  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic]))

(defn paso1 [] (run* [q] (== 1 1))) ;; (_.0)

(defn paso1 [] (run* [q] (== q 1))) ;; (1)

(defn paso100 [] (run 1 [q]))

(defn paso400 [] (run 2 [q] (conde [(== q 3)] [(== q 5)])))

(def  principal paso1)

(defn -main [] (println ( principal ) ))
