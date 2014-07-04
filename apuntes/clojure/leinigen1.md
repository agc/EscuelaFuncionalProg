#Clojure Primeros pasos
## Referencias
[Tutorial 1](http://www.creativeapplications.net/tutorials/introduction-to-clojure-part-1/)
[Tutorial 2](https://github.com/technomancy/leiningen/blob/master/doc/TUTORIAL.md)
[Tutorial 3](http://learn-clojure.com/clojure_tutorials.html)
[Tutorial 4](http://www.aidanf.net/2014/02/06/getting-setup-with-clojure.html)
## Leinigen. Operaciones básicas

_Creación de un proyecto_  `lein new prog_logica`

La estructura de un proyecto 

```
  project.clj
  README
  src
  	prog_logica
        	core.clj
  test
	prog_logica
        	core_test.clj
```



El archivo de proyecto _project.clj_

```
(defproject prog_logica "0.1.0-SNAPSHOT"
  :description "Ejemplos de programácion lógica"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]])
```

Para descargar las dependencias `lein deps` . Para ver las dependencias `lein classpath`

**Comandos útiles** `lein repl`  `lein test` `lein run `   `lein interactive`
## Ides
_NightCode_

** Programación lógica

```
(run 1 [q])
(run 1 [q] (== 4 q))

(run 1 [q] (== 4 q) (== 3 q))

(run 2 [q] (conde [(== q 3)] [(== q 5)]))

 (run 2 [q]   (conde [(== 4 q) (== 7 q)]  [(== 5 q)]))
```



jjjj