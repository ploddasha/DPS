(ns task1.core)

(defn remove-same-last-letter
  [word alphabet]
  (let [last-letter (last word)]
    (doall (remove (fn [letter] (= (str "" last-letter)  letter)) alphabet))))


(defn add-letter
  [alphabet words]
  (mapcat (fn [word]
            (let [cleaned-alphabet (remove-same-last-letter word alphabet)]
              (if (seq cleaned-alphabet)
                (for [letter cleaned-alphabet]
                  (str word letter))
                [word])))
          words))

(defn main
  [alphabet n]
  (reduce (fn [acc _] (add-letter alphabet acc)) (list "") (range n)))

(println (main ["a" "b" "c"] 2))