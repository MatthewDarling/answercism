(ns pangram
  (:require [clojure.string :as str]))

(defn pangram?
  [input]
  (-> input
      str/lower-case
      (str/replace #"[^a-z]" "")
      (->> (reduce conj #{}))
      count
      (= 26)))
