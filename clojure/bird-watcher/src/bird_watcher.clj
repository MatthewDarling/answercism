(ns bird-watcher)

(def last-week 
  [0 2 5 3 7 8 4])

(defn today [birds]
  (last birds))

(defn inc-bird [birds]
  (conj (->> birds butlast vec)
        (-> birds last inc)))

(defn day-without-birds? [birds]
  (boolean (some zero? birds)))

(defn n-days-count [birds n]
  (reduce + (take n birds)))

(defn busy-days [birds]
  (count (filter #(>= % 5) birds)))

(defn conj-without-consecutive
  [coll prospective-item]
  (if (not= (last coll) prospective-item)
    (conj coll prospective-item)
    coll))

(defn odd-week? [birds]
  (let [odds (map odd? birds)]
    (->> odds
         (reduce conj-without-consecutive [])
         (= odds))))
