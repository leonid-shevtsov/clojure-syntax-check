(ns clojure-syntax-check.app-test
  (:require [clojure.test :refer :all]
            [clojure-syntax-check.app :refer :all]))

(deftest skip-whitespace-test
  (testing "skip-whitespace"
    (testing "inside line"
      (is (= (skip-whitespace "    (content") "(content"))
      )
    (testing "empty lines"
      (is (= (skip-whitespace "\n\n\n(content") "(content"))
      )
    (testing "whitespace lines"
      (is (= (skip-whitespace "\n   \n   \n   (content") "(content"))
      )
    (testing "comment lines"
      (is (= (skip-whitespace "\n\n\n;comment\n\n(content") "(content"))
      )
    (testing "comment with offset"
      (is (= (skip-whitespace "\n\n\n \t ;comment\n\n(content") "(content"))
      )
    )
  )
