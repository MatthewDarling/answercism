(ns grade-school)

(defn grade [school grade]
  (or (get school grade) []))

(defn add [school name grade]
  (update school
          grade
          #(if (coll? %1)
             (conj %1 %2)
             [%2])
          name))

(defn sorted [school]
  (-> school
      (update-vals (comp vec sort))
      (->> (into (sorted-map)))))
