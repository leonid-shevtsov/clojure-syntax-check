(ns clojure-syntax-check.app-test
  (:require [clojure.test :refer :all]
            [clojure-syntax-check.app :refer :all]))

(deftest sanitize-message-test
  (testing "sanitize-message"
    (testing "replaces java.lang.RuntimeException in the beginning of line"
      (is (= (sanitize-message "java.lang.RuntimeException: foo") "foo"))
      )
    (testing "preserves java.lang.RuntimeException inside of line"
      (is (= (sanitize-message "bar java.lang.RuntimeException: foo") "bar java.lang.RuntimeException: foo"))
      )
    )
  )

(deftest parse-error-message-test
  (testing "parse-error-message"
    (testing "extracts mentioned line number from error message"
      (is (= (parse-error-message "java.lang.RuntimeException: foo, starting at line 1" 2) ["foo" 1]))
      )
    (testing "falls back on provided line number"
      (is (= (parse-error-message "java.lang.RuntimeException: foo" 2) ["foo" 2]))
      )
    )
  )
