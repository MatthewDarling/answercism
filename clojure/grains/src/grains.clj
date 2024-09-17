(ns grains)

(comment
  (defn square-broken
    "Clojure's BigInt class doesn't support bit shifts, this throws an exception"
    [square-num]
    (bit-shift-left (bigint 1) (dec square-num))))

(defn square
  "Clojure's bit-shift-left helper also falsely claims the Java BigInteger class
  doesn't support bit shifts, so we have to use the Java method"
  [square-num]
  (.shiftLeft (biginteger 1) (dec square-num)))

(defn total
  []
  (dec (square 65)))
