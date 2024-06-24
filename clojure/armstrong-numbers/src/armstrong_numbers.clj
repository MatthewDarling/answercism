(ns armstrong-numbers
  (:require [clojure.math :as math]))

(defn armstrong? [num]
  (let [length (-> num str count)
        pow-seq (->> num
                     str
                     (map (comp parse-long str))
                     (map #(Math/pow % length)))]
    (= num (long (reduce + pow-seq)))))
