(ns acronym
  (:require [clojure.string :as str]))

(defn acronym
  "Converts phrase to its acronym."
  [phrase]
  (->> phrase
       ;; return sequences of matches for either entirely lower-cased words,
       ;; or a run of capital letters optionally followed by a run of lower-case
       ;; run of all-caps handles PHP
       ;; run of all-caps then lower-case handles HyperText
       ;; spaces and dashes aren't letters so it splits there too
       (re-seq #"[A-Z]+[a-z]*|[a-z]+")
       (map first)
       str/join
       str/upper-case))
