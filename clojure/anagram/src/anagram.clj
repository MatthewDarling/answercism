(ns anagram
  (:require [clojure.string :as str]))

(defn normalized-anagram-compare
  [word potential-anagram]
  (let [lower-case-word (str/lower-case word)
        lower-case-potential-anagram (str/lower-case potential-anagram)]
    (and (not= lower-case-word lower-case-potential-anagram)
         (= (sort lower-case-word)
            (sort lower-case-potential-anagram)))))

(defn anagrams-for [word prospect-list]
  (filter (partial normalized-anagram-compare word) prospect-list))
