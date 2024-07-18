(ns nth-prime
  (:require [clojure.math :as math]))

(defn divisible-by
  [num divisor]
  (zero? (mod num divisor)))

(defn range-inclusive
  [start end]
  (concat (range start end) [end]))

(defn prime?
  [n]
  (let [rounded-down-sqroot (int (math/sqrt n))]
    (or (and (not= 1 n)
             (= 1 rounded-down-sqroot))
        (not
         (some (partial divisible-by n)
               (range-inclusive 2 rounded-down-sqroot))))))

(defn nth-prime
  "Returns the prime number in the nth position."
  [n]
  (if (<= n 0)
    (throw (IllegalArgumentException. "Can only generate primes of 1+"))
    (last (take n (filter prime? (rest (range)))))))
