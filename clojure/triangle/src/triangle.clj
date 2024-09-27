(ns triangle)

(defn compare-sides
  [[a b c]]
  (>= (+ a b) c))

(defn is-valid?
  "A triangle's list of sides is valid iff:
    - All sides are greater than zero
    - The sum of any two sides is greater than the remaining side alone

  To confirm the second clause, we create an infinite cycle of the sides, and
  iterate over it to get all three permutations to compare with."
  [sides]
  (let [sides-cycle (cycle sides)]
    (and (every? pos? sides)
         (compare-sides sides-cycle)
         (compare-sides (rest sides-cycle))
         (compare-sides (drop 2 sides-cycle)))))

(defn equilateral?
  [& sides]
  (and (is-valid? sides)
       (= 1 (count (set sides)))))

(defn isosceles?
  [& sides]
  (and (is-valid? sides)
       (< (count (set sides)) 3)))

(defn scalene?
  [& sides]
  (and (is-valid? sides)
       (= 3 (count (set sides)))))
