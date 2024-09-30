(ns perfect-numbers)

(defn factors
  [num]
  (let [highest-possible-factor (inc (/ num 2))]
    (->> highest-possible-factor
         (range 1)
         (filter #(zero? (mod num %))))))

(defn classify
  [num]
  (if (neg? num)
    (throw (IllegalArgumentException.))
    (let [aliquot (reduce + (factors num))]
      (cond
        (= aliquot num) :perfect
        (> aliquot num) :abundant
        :else :deficient))))
