(ns raindrops)

(defn divisible-by?
  [num div]
  (zero? (mod num div)))

(defn any-factor?
  [num factors]
  (->> factors
       (map #(mod num %))
       (apply min)
       zero?))

(defn convert
  [input]
  (cond-> ""
    (divisible-by? input 3) (str "Pling")
    (divisible-by? input 5) (str "Plang")
    (divisible-by? input 7) (str "Plong")
    ;; Will only match if none of the above did
    (not (any-factor? input [3 5 7])) (str input)))
