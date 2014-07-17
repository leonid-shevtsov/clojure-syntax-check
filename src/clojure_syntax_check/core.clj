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
  (let [stream (if filename (io/input-stream filename) *in*)
        result (app/validate stream)]
    (if result
      (do
        (println (str (:line result) ": " (:message result)))
        (System/exit 1)
        )
      (System/exit 0)
      )
    )
  )
