(ns clojure-syntax-check.core
  (:require
    [clojure.java.io :as io]
    [clojure-syntax-check.app :as app]
  )
  (:gen-class :main true)
  )

(defn -main
  "Try to parse standard input for"
  [filename]
  (app/validate
    (if filename
      (io/input-stream filename)
      *in*
      )
    )
  )
