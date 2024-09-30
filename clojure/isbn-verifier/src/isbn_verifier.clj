(ns isbn-verifier
  (:require [clojure.string :as str]))

(defn place-value
  [place isbn-char]
  (if (= \X isbn-char)
    10
    (* (-> isbn-char str parse-long) (- 10 place))))

(defn isbn?
  [isbn]
  (let [normalized (str/replace isbn #"-" "")
        check-char (last normalized)]
    (and (= 10 (count normalized))
         (every? #{\1 \2 \3 \4 \5 \6 \7 \8 \9 \0 \X} normalized)
         (or (Character/isDigit check-char) (= \X check-char))
         (-> normalized
             (->> (map-indexed place-value)
                  (reduce +))
             (mod 11)
             zero?))))
