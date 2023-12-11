(ns task3.core-test
  (:require [clojure.test :refer :all]
            [task3.core :refer :all]))

(deftest test-p-filter
  (is (= (take 10 (p-filter heavy-even? (range 100))) (range 0 20 2)))
  (is (= (take 10 (p-filter heavy-even? (iterate inc 2))) (range 2 21 2)))
  (is (= (take 10 (p-filter heavy-even? (range 0))) (filter even? (range 0))))
  (is (= (p-filter even? (range 10000)) (filter even? (range 10000))))
  )
