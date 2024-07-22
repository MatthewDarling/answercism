(ns roman-numerals
  (:require [clojure.math :as math]
            [clojure.string :as str]))

;;;| M    | D   | C   | L   | X   | V   | I   |
;;;| ---- | --- | --- | --- | --- | --- | --- |
;;;| 1000 | 500 | 100 | 50  | 10  | 5   | 1   |

(defn digit->roman
  "Given a `digit` of Arabic numerals, and a `digit-pow` to mark its power of ten,
  return a string of its representation in Roman numerals."
  [digit-pow digit]
  (let [digit-as-num (if-not (number? digit)
                       (read-string (str digit))
                       digit)
        full-value (int (* digit-as-num (math/pow 10 digit-pow)))]
    (case full-value
      3000 "MMM"
      2000 "MM"
      1000 "M"
      900 "CM"
      800 "DCCC"
      700 "DCC"
      600 "DC"
      500 "D"
      400 "CD"
      300 "CCC"
      200 "CC"
      100 "C"
      90 "XC"
      80 "LXXX"
      70 "LXX"
      60 "LX"
      50 "L"
      40 "XL"
      30 "XXX"
      20 "XX"
      10 "X"
      9 "IX"
      8 "VIII"
      7 "VII"
      6 "VI"
      5 "V"
      4 "IV"
      3 "III"
      2 "II"
      1 "I"
      0 "")))

(defn numerals
  [num]
  (let [num-str (str num)
        num-digit-count (count num-str)]
    (str/join
     (reduce #(conj %1
                   (digit->roman (- num-digit-count
                                    (inc (first %2)))
                                 (second %2)))
            []
            (map-indexed vector num-str)))))
