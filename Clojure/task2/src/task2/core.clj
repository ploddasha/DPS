(ns task2.core)

;; Определение функции для генерации простых чисел с использованием решета Эратосфена
(defn sieve-of-eratosthenes
  [s]
  (lazy-seq
    (when-let [head (first s)]
      (cons head (sieve-of-eratosthenes
                   (filter #(not= 0 (rem % head)) (next s)))))))

;; Генерация бесконечной последовательности простых чисел
(def primes (sieve-of-eratosthenes (iterate inc 2)))

;; Вывод первых 10 простых чисел
(println (take 10 primes))