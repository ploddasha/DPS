(ns task5.core)

(def restarts (atom 0 :validator #(>= % 0)))

(defn philosopher [number left right eating-time thinking-time dining-number]
  (let [left-fork (-> left :fork)
        left-counter (-> left :counter)
        right-fork (-> right :fork)
        right-counter (-> right :counter)]
    (dotimes [_ dining-number]
      (do
        (println (str "The philosopher " number " is thinking"))
        (Thread/sleep thinking-time)
        (dosync
          (swap! restarts inc)
          (swap! left-counter inc)
          (alter left-fork inc)
          (println (str "The philosopher " number " picked up the left fork"))
          (swap! right-counter inc)
          (alter right-fork inc)
          (println (str "The philosopher " number " picked up the right fork"))
          (Thread/sleep eating-time)
          (println (str "The philosopher " number " has finished eating"))
          (swap! restarts dec))))))

(defn dining-philosophers [eating-time length-of-thinking number-of-philosophers dining-number]
  (let [forks (doall (map (fn [_] (-> {:fork (ref 0), :counter (atom 0)})) (range number-of-philosophers)))
        philosophers (map #(new Thread (fn [] (philosopher % (nth forks %) (nth forks (mod (inc %) number-of-philosophers)) eating-time length-of-thinking dining-number))) (range number-of-philosophers))]
    (doall (map #(.start %) philosophers))
    (doall (map #(.join %) philosophers))))

(let [eating-time 1000
      length-of-thinking 1000
      number-of-philosophers 4
      dining-number 3]
  (time (dining-philosophers eating-time length-of-thinking number-of-philosophers dining-number)))

(println "Transaction restarts: " @restarts)
