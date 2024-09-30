(ns minesweeper
  (:require [clojure.string :as str]))

(defn check-neighbours
  "Return a count of the mines present in the neighbours of cell (y,x)."
  [split-board y x]
  (count
   (for [column (range (max (dec y) 0) (inc (min (inc y) (-> split-board first count))))
         row (range (max (dec x) 0) (inc (min (inc x) (count split-board))))
         :when (not= [y x] [column row])
         :when (= \* (get-in split-board [column row]))]
     :mine)))

(defn draw
  [board]
  (if-not (seq board)
    ""
    (let [split-board (str/split-lines board)]
      (str/join "\n"
       (map-indexed (fn [y row]
                     (str/join ""
                               (map-indexed (fn [x cell]
                                              (if (= \* cell)
                                                \*
                                                (let [mine-count (check-neighbours split-board y x)]
                                                  (if (zero? mine-count)
                                                    " "
                                                    (first (str mine-count))))))
                                            row)))
                 split-board)))))
