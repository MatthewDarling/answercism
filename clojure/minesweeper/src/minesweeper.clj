(ns minesweeper
  (:require [clojure.string :as str]))

(defn check-neighbours
  "Return a count of the mines present in the neighbours of cell (y,x)."
  [split-board y x]
  (count
   (for [row (range (dec y) (+ 2 y))
         column (range (dec x) (+ 2 x))
         :when (< -1 row (count split-board))
         :when (< -1 column (count (first split-board)))
         :when (not= [y x] [row column])
         :when (= \* (get-in split-board [row column]))]
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
