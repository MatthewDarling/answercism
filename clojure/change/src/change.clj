(ns change)

(defn compute-permutation
  [target remaining-coins coin-list]
  (if (zero? target)
    coin-list
    (let [[count value] (->> remaining-coins
                             (map #(vector (int (/ target %)) %))
                             (remove (comp zero? first))
                             flatten
                             (apply hash-map)
                             sort
                             first)]

      (if value
        (recur (mod target value)
               (disj remaining-coins value)
               (concat coin-list (repeat count value)))
        []))))

(defn issue
  [target coin-set]
  (cond
    (neg? target) (throw (IllegalArgumentException. "cannot change"))
    (zero? target) []
    :else (let [valid-coins (set (filter (partial >= target) coin-set))]
            (if (zero? (count valid-coins))
              (throw (IllegalArgumentException. "cannot change"))
              (loop [coins-to-check valid-coins
                     coin-lists []]
                (if (pos? (count coins-to-check))
                  (recur (disj coins-to-check (last (sort coins-to-check)))
                         (conj coin-lists (compute-permutation target coins-to-check [])))
                  (if-let [shortest-sequence (seq (sort (first (sort-by count coin-lists))))]
                    shortest-sequence
                    (throw (IllegalArgumentException. "cannot change")))))))))
