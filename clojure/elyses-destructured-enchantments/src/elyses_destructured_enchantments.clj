(ns elyses-destructured-enchantments)

(defn first-card
  "Returns the first card from deck."
  [[card & rest]]
  card)

(defn second-card
  "Returns the second card from deck."
  [[_ card & rest]]
  card)

(defn swap-top-two-cards
  "Returns the deck with first two items reversed."
  [[first-card second-card & rest]]
  (concat [second-card first-card] rest))

(defn discard-top-card
  "Returns a sequence containing the first card and
   a sequence of the remaining cards in the deck."
  [[card & rest]]
  [card rest])

(def face-cards
  ["jack" "queen" "king"])

(defn insert-face-cards
  "Returns the deck with face cards between its head and tail."
  [[first-card & rest]]
  (let [first-seq (if first-card [first-card] [])]
    (concat first-seq face-cards rest)))
