(ns task3.core)

(defn p-filter
  [pred sequence]
  (let [core-number (.availableProcessors (Runtime/getRuntime))
        chunk-size 100
        part-size (int (Math/ceil (/ chunk-size core-number)))
        chunks (partition-all chunk-size sequence)]
    (->> chunks
         (map (fn [chunk]
                (let [parts (partition-all part-size chunk)]
                  (->> parts
                       (map (fn [part]
                              (future (doall (filter pred part)))))
                       (doall)
                       (map deref)
                       (apply concat)))))
         (apply concat))))

(defn heavy-even?
  [arg]
  (Thread/sleep 10)
  (even? arg))

(time (p-filter heavy-even? (range 1000000)))
(time (filter heavy-even? (range 1000000)))