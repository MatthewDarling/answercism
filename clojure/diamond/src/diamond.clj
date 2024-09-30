(ns diamond
  (:require [clojure.string :as str]))

(defn join-str
  [& args]
  (str/join (flatten args)))

(defn with-middle-spaces
  [index letter]
  (if (zero? index)
    letter
    (let [space-count (inc (* 2 (dec index)))]
      (join-str letter (repeat space-count " ") letter))))

(defn center-string
  [string desired-length]
  (let [missing (- desired-length (count string))]
    (if-not (pos? missing)
      string
      (join-str (repeat (/ missing 2) " ")
                string
                (repeat (/ missing 2) " ")))))

(defn reflect-seq
  [seq]
  (concat seq (rest (reverse seq))))

(defn diamond
  [letter]
  (let [ascii-val (int letter)
        letter-num (-> ascii-val (- (int \A)) inc)
        max-width (dec (* 2 letter-num))]
    (->> ascii-val
         inc
         (range (int \A))
         (map (comp str char))
         (map-indexed with-middle-spaces)
         (map #(center-string %1 max-width))
         reflect-seq)))
