(ns card-games)

(defn rounds
  "Takes the current round number and returns 
   a `list` with that round and the _next two_."
  [n]
  (range n (+ n 3)))

(defn concat-rounds 
  "Takes two lists and returns a single `list` 
   consisting of all the rounds in the first `list`, 
   followed by all the rounds in the second `list`"
  [l1 l2]
  (concat l1 l2))

(defn contains-round? 
  "Takes a list of rounds played and a round number.
   Returns `true` if the round is in the list, `false` if not."
  [l n]
  (boolean (some #{n} l)))

(defn card-average
  "Returns the average value of a hand"
  [hand]
  (/ (reduce + hand) (float (count hand))))

(defn approx-average?
  "Returns `true` if average is equal to either one of:
  - Take the average of the _first_ and _last_ number in the hand.
  - Using the median (middle card) of the hand."
  [hand]
  (let [average (card-average hand)
        first-and-last-fn (juxt first last)
        ;; int rounds down, which is desirable here
        first-half (int (/ (count hand) 2))]
    (or (= average (->> hand first-and-last-fn card-average))
        (= average (->> hand (drop first-half) first float)))))

(defn average-even-odd?
  "Returns true if the average of the cards at even indexes 
   is the same as the average of the cards at odd indexes."
  [hand]
  (let [indexed-hand (map-indexed vector hand)
        evens-average (->> indexed-hand (filter (comp even? first)) (map second) card-average)
        odds-average (->> indexed-hand (filter (comp odd? first)) (map second) card-average)]
    (= evens-average odds-average)))

(defn maybe-double-last
  "If the last card is a Jack (11), doubles its value
   before returning the hand."
  [hand]
  (if (not= 11 (last hand))
    hand
    (concat (drop-last hand) [22])))
