(ns collatz-conjecture)

(defn collatz
  ([num]
   (collatz num 0))
  ([num step-count]
   (cond
     (<= num 0) (throw (ex-info "Invalid number for Collatz" {:num num :step-count step-count}))
     (= num 1) step-count
     :else (let [next-num (if (even? num)
                            (/ num 2)
                            (inc (* 3 num)))]
             (recur next-num (inc step-count))))))
