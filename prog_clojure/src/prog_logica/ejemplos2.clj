(ns prog-logica.ejemplos2

  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic])
  (:require [clojure.core.logic.pldb :as pldb]
            ))



(defn -main [] (println ( " ejemplos2" ) ))



(pldb/db-rel father Father Child)
(pldb/db-rel mother Mother Child)

(def hechos (pldb/db [ father 'Vito 'Michael]
               [father 'Vito 'Sonny]
               [father 'Vito 'Fredo]
               [father 'Michael 'Anthony]
               [father 'Michael 'Mary]
               [father 'Sonny 'Vicent]
               [father 'Sonny 'Francesca]
               [father 'Sonny 'Kathryn]
               [father 'Sonny 'Frank]
               [father 'Sonny 'Santino]

               [mother 'Carmela 'Michael]
               [mother 'Carmela 'Sonny]
               [mother 'Carmela 'Fredo]
               [mother 'Kay 'Mary]
               [mother 'Kay 'Anthony]
               [mother 'Sandra 'Francesca]
               [mother 'Sandra 'Kathryn]
               [mother 'Sandra 'Frank]
               [mother 'Sandra 'Santino]))

(def prueba1
 (pldb/with-db hechos
   (doall
              (run* [q]
  (father 'Vito q)))))

(def prueba2
   (pldb/with-db hechos
     (doall
             ( run* [q]
  (father q 'Michael)))))

 (defn parent [p child]
    (conde
      ((father p child))
      ((mother p child))))

(defn grandparent [gparent child]
  (fresh [p]
    (parent gparent p)
    (parent p child)))

(def prueba3
   (pldb/with-db hechos
     (doall
             ( run* [q]
  (parent q 'Michael)))))


(def prueba4
   (pldb/with-db hechos
     (doall
             ( run* [q]
  (grandparent q 'Anthony)))))

(def prueba5
   (pldb/with-db hechos
     (doall
             ( run* [q]
  (grandparent 'Vito q )))))


(def prueba6
   (pldb/with-db hechos
     (doall
             ( run* [q]

 (fresh [gparent p child]
    (== p 'Michael)
    (parent p child)
    (grandparent gparent child)
    (== q {:grandparent gparent :parent p :child child}) )))))








