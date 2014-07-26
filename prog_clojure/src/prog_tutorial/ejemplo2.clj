;(load "ejemplo2")


(defn funcion1 [[x y _ _  :as todo]]
  " Uso de desestructuracion de secuencia en el argumento de una función
  Puede llamarse con un vector/lista de dos o mas elementos"
  ( do
     (println (+ x y))
      (println todo)))


(defn funcion2 [ {a :a b :b :as todo}]
   " Uso de desestructuracion de map en el argumento de una función
  Puede llamarse con un map que tenga esas claves :a :b"
  ( do
     (println (+ a b))
      (println todo)))


(defn funcion3 [ { :keys [a b] :as todo}]
   " Uso de desestructuracion de map, usando keys en el argumento de una función
  Puede llamarse con un map que tenga esas claves :a :b"
  ( do
     (println (+ a b))
      (println todo)))
