(ns prog-monadas.monadas10)

(defn -main [] (do
	(println "Hola ")
	(println "Monadas ")
	(println "clojure")))

(def lista-m {
              :return (fn [v] (list v))
              :bind (fn [vm f] (mapcat f vm))})


(defn combinaciones []
  (let [ bind (:bind lista-m)
         return (:return lista-m)]
        (bind [1 2 3] (fn [a]
                        (bind [4 5] (fn [b]
                                      (return [a b])))))))


(defn m-steps [m [name val & bindings] body]
  (if (seq bindings)
    `(-> ~val ((:bind ~m) (fn [~name] ~(m-steps m bindings body))))

    `(-> ~val ((:bind ~m) (fn [~name] ((:return ~ m)  ~body))))))


  (defmacro do-m [m bindings body] (m-steps m bindings body))

  (defn combi []
    (do-m lista-m [a [ 1 2 3] b [4 5] c [:a :b :c]] [a b c])
    )
