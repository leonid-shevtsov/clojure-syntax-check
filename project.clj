(defproject clojure-syntax-check "0.1.0"
  :description "A syntax checker for Clojure"
  :url "http://github.com/leonid-shevtsov/clojure-syntax-check"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :plugins [[lein-autoreload "0.1.0"]]
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main clojure-syntax-check.core
  :aot [clojure-syntax-check.core]
  )
