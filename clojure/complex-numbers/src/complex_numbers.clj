(ns complex-numbers
  (:require [clojure.math :as math]))

(defn real
  [[a b]]
  a)

(defn imaginary
  [[a b]]
  b)

(defn square-imaginary
  [[a b]]
  (+ (math/pow a 2)
     (math/pow b 2)))

(defn abs
  [[a b]]
  (math/sqrt (square-imaginary [a b])))

(defn conjugate
  [[a b]]
  [a (* b -1)])

(defn add
  [[a b] [c d]]
  [(+ a c) (+ b d)])

(defn sub
  [[a b] [c d]]
  [(- a c) (- b d)])

(defn mul
  [[a b] [c d]]
  [(- (* a c)
      (* b d))
   (+ (* b c)
      (* a d))])

(defn div
  [[a b] [c d]]
  [(/ (+ (* a c)
         (* b d))
      (square-imaginary [c d]))
   (/ (- (* b c)
         (* a d))
      (square-imaginary [c d]))])
