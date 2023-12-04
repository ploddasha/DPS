(ns task2.core-test
  (:require [clojure.test :refer :all]
            [task2.core :refer :all]))

(deftest test-sieve-of-eratosthenes
  (let [test-sequence (range 2 20)
        result (sieve-of-eratosthenes test-sequence)]
    (is (= result '(2 3 5 7 11 13 17 19)))))

(deftest test-sieve-of-eratosthenes-of-thousand
  (let [test-sequence (range 2 1110)
        result (sieve-of-eratosthenes test-sequence)
        primes-in-range (filter #(and (>= % 1100) (<= % 1110)) result)]
    (is (= primes-in-range '(1103 1109)))))

(deftest test-primes-sequence
  (let [num-primes-to-check 10
        primes-result (take num-primes-to-check primes)
        expected-primes [2 3 5 7 11 13 17 19 23 29]]
    (is (= expected-primes primes-result))))

(deftest test-100th-prime
  (let [num-primes-to-check 185
        primes-result (nth primes (dec num-primes-to-check))]
    (is (= 1103 primes-result))))


