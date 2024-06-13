(ns grains)

(defn square-broken
  "Clojure's BigInt class doesn't support bit shifts"
  [square-num]
  (bit-shift-left (bigint 1) (dec square-num)))

(defn square
  "Clojure's bit-shift-left helper also falsely claims the Java BigInteger class
  doesn't support bit shifts"
  [square-num]
  (.shiftLeft (biginteger 1) (dec square-num)))

(defn total
  []
  (dec (square 65)))
