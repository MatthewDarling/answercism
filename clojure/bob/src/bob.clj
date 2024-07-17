(ns bob
  (:require [clojure.string :as str]))

(defn upper-case-character?
  [char]
  (and (java.lang.Character/isLetter char)
       (java.lang.Character/isUpperCase char)))

(defn all-upper-case?
  [s]
  (let [alpha-only (str/replace s #"[^\p{Alpha}]" "")]
    (and (seq alpha-only)
         (every? upper-case-character? alpha-only))))

(defn response-for [s]
  (let [normalized-input (str/trim s)
        silence? (= "" normalized-input)
        question? (and (not silence?)
                       (str/ends-with? normalized-input "?"))
        yelling? (and (not silence?)
                      (all-upper-case? normalized-input))]
    (cond
      (and yelling? question?) "Calm down, I know what I'm doing!"
      question? "Sure."
      yelling? "Whoa, chill out!"
      silence? "Fine. Be that way!"
      :else "Whatever.")))
