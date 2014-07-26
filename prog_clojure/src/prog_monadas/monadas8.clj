; valores monádicos son funciones s->[valor s]
; el valor monádico es el cómputo

; el estado s será un map con pares nombre-variable valor

; [a vm] el resultado de cualquiera de las funciones fetch-val set-val se
; podrá colocar en esa posición
; [a vm ] ---->(m-bind vm (fn [a]......))   la funcion fn[a] deberá ser monadica

(defn lee-val [key]
  (fn [s] [(key s) s]))

(defn escribe-val [key val]
  (fn[s] (let [old-val (get   s key)
               new-s   (assoc s key val)]
           [old-val new-s])))


(defn copy-val [from to]
  (domonad state-m
       [from-val    (lee-val from )
        old-to-val  (escribe-val to from-val)]
           old-to-val ))

; devuelve un valor monadico f(s) que cambia el estado como set-val y deja el valor old-to-val

(def prueba-monada
  (let [estado-inicial {:a 1 :b 2}
        computacion (copy-val :b :a)  ; es un valor monadico, ahora lo ejecutamos
        [resultado estado-final] (computacion estado-inicial)]
        estado-final
    )
  )
