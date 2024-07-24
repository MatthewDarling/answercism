(ns rotational-cipher)

(def lowest-upper-ascii 65)
(def highest-upper-ascii 90)
(def lowest-lower-ascii 97)
(def highest-lower-ascii 122)

(defn alphabet-plus
  "If `a` + `b` is over boundary, wrap around the alphabet by subtracting by the
  count of characters, i.e. 26."
  [boundary a b]
  (let [combined (+ a b)]
    (if (> combined boundary)
      (- combined 26)
      combined)))

(defn rotate-ascii
  [ascii-val key]
  (cond
    (<= lowest-upper-ascii ascii-val highest-upper-ascii)
    (alphabet-plus highest-upper-ascii ascii-val key)

    (<= lowest-lower-ascii ascii-val highest-lower-ascii)
    (alphabet-plus highest-lower-ascii ascii-val key)

    :else ascii-val))

(defn rotate-char
  [input-char key]
  (char (rotate-ascii (int input-char) key)))

(defn rotate
  [input key]
  (apply str (map #(rotate-char % key) input)))
