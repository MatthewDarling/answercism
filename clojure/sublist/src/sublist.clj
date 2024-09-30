(ns sublist)

(defn compare-slices
  [list1 list2]
  (let [length-one (count list1)]
    (loop [remaining-to-compare list2]
      (or (= list1 (take length-one remaining-to-compare))
          (when (seq remaining-to-compare)
            (recur (rest remaining-to-compare)))))))

(defn classify
  [list1 list2]
  (let [length-one (count list1)
        length-two (count list2)]
    (cond
      (and (= length-one length-two) (= list1 list2)) :equal
      (and (< length-one length-two) (compare-slices list1 list2)) :sublist
      (and (> length-one length-two) (compare-slices list2 list1)) :superlist
      :else :unequal)))
