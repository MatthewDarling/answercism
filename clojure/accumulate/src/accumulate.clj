(ns accumulate)

(defn accumulate
  [func coll]
  (loop [accum []
         remaining coll]
    (if (empty? remaining)
      accum
      (recur (conj accum (func (first remaining)))
           (drop 1 remaining)))))
