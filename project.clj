(defproject clojure-syntax-check "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-autoreload "0.1.0"]]
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main clojure-syntax-check.core
  :aot [clojure-syntax-check.core]
  )
