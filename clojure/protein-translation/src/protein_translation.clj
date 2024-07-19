(ns protein-translation
  (:require [clojure.string :as str]))

(def translate-codon
  {"AUG" "Methionine"
   "UUU" "Phenylalanine"
   "UUC" "Phenylalanine"
   "UUA" "Leucine"
   "UUG" "Leucine"
   "UCU" "Serine"
   "UCC" "Serine"
   "UCA" "Serine"
   "UCG" "Serine"
   "UAU" "Tyrosine"
   "UAC" "Tyrosine"
   "UGU" "Cysteine"
   "UGC" "Cysteine"
   "UGG" "Tryptophan"
   "UAA" "STOP"
   "UAG" "STOP"
   "UGA" "STOP"})

(defn reducing-conj-end-with-stop
  [acc new-val]
  (if (= "STOP" new-val)
    (reduced acc)
    (conj acc new-val)))

(defn translate-rna
  [rna-sequence]
  (->> rna-sequence
       (partition-all 3)
       (map str/join)
       (reduce #(reducing-conj-end-with-stop %1 (translate-codon %2)) [])))
