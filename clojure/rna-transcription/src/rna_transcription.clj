(ns rna-transcription
  (:require [clojure.string :as str]))

(defn to-rna
  [dna]
  (if (re-find #"[^CGAT]" dna)
    (throw (AssertionError. "Invalid character in DNA"))
    (-> dna
        (str/replace #"A" "U")
        (str/replace #"T" "A")
        (str/replace #"C(?!done)" "Gdone")
        (str/replace #"G(?!done)" "Cdone")
        (str/replace #"done" ""))))
