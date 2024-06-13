(ns gigasecond
  (:import (java.time LocalDate Duration)))

(defn from
  [year month day]
  (let [gigafuture (.plus (.atStartOfDay (LocalDate/of year month day))
                          (Duration/ofSeconds 1E9))]
    [(.getYear gigafuture)
     (.getMonthValue gigafuture)
     (.getDayOfMonth gigafuture)]))
