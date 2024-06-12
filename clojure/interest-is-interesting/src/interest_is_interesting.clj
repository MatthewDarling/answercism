(ns interest-is-interesting)

(defn interest-rate
  "Returns the interest rate based on the specified balance."
  [balance]
  (cond
    (< balance 0) -3.213
    (and (<= 0 balance) (< balance 1000)) 0.5
    (and (<= 1000 balance) (< balance 5000)) 1.621
    (<= 5000 balance) 2.475))

(defn annual-balance-update
  "Returns the annual balance update, taking into account the interest rate.

  Strangely, the tests only work with the proper precision in this
  add-balance-to-interest format. Adding 1 to the interest rate and multiplying,
  which is theoretically mathematically equivalent, seems to have different
  rounding behaviour. I was getting far more decimal places than the tests are
  looking for. Some kind of smart rounding in the BigDecimal class itself...?

  Anyway, annoying to deal with. Had to borrow this from someone else's
  solution."
  [balance]
  (+ balance
     (* (bigdec (interest-rate balance))
        0.01M
        (abs balance))))

(defn int-to-percent
  "cent, from the Latin centum, refers to 100. Thus a per-cent as integer must be
  divided by 100."
  [percent]
  (/ percent 100M))

(defn amount-to-donate
  "Returns how much money to donate based on the balance and the tax-free
  percentage. Also we're stingy and only give whole dollar amounts."
  [balance tax-free-percentage]
  (let [pos-balance-only (max balance 0)]
    (->> tax-free-percentage
       (* 2)
       int-to-percent
       (* pos-balance-only)
       int)))
