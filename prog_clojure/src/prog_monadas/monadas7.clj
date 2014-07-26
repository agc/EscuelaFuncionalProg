; monad maybe-m
; el valor monadico es un valor ordinario
; lo único que se modifica es m-bind que examina si el argumento es nil
; y devuelve nil



(defn raizcuadrada [x]
  (domonad maybe-m
           [radicando x
            :when (>= x 0)]                  ; se obliga a usar m-bind con x para detectar
           (Math/pow radicando 0.5)))        ; nil

(defn funcion-inversa [x]
  (domonad maybe-m
           [denominador x
            :when (or (> x 0) (< x 0))]
           (/ 1 denominador)))


(defn componer [f1 f2]
  (fn [x]
  (domonad maybe-m
           [a  (f2 x)
            b  (f1 a)]
           b
           )

  ))
