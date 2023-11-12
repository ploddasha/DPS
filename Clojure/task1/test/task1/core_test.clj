(ns task1.core-test
  (:require [clojure.test :refer :all]
            [task1.core :refer :all]))

(deftest test-remove-same-last-letter
  (is (= ["a" "b" "c"]
         (remove-same-last-letter "word" ["a" "d" "b" "c"]))))

(deftest test-add-letter
  (is (= ["dda" "ddb" "ddc" "bba" "bbc"]
         (add-letter ["a" "b" "c"] ["dd" "bb"]))))

(deftest test-main
  (is (= ["ab" "ac" "ba" "bc" "ca" "cb"]
         (main ["a" "b" "c"] 2))))

(deftest test-main2
  (is (= ["aba" "abc" "aca" "acb" "bab" "bac" "bca" "bcb" "cab" "cac" "cba" "cbc"]
         (main ["a" "b" "c"] 3))))

(deftest test-main3
  (is (= ["ababa" "babab"]
         (main ["a" "b"] 5))))
