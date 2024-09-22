(ns all-your-base)

(defn valid-request?
  [input-base num-seq target-base]
  (and (every? (complement neg?) num-seq)
       (every? #(< % input-base) num-seq)
       (>= input-base 2)
       (>= target-base 2)))

(defn biggest-power-of-base
  ([decimal-num target-base]
   (biggest-power-of-base decimal-num target-base 0))
  ([decimal-num target-base current-pow]
   (let [next-pow (inc current-pow)]
     (if (< decimal-num (Math/pow target-base next-pow))
       current-pow
       (recur decimal-num target-base next-pow)))))

(defn decimal-to-base
  [decimal-num target-base]
  (loop [output []
         current-pow (biggest-power-of-base decimal-num target-base)
         remaining decimal-num]
    (if (neg? current-pow)
      output
      (let [;; Math/pow returns doubles, we're just dealing with ints
            divisor (int (Math/pow target-base current-pow))
            ;; casting to int rounds down
            quotient (int (/ remaining divisor))]
        (recur (conj output quotient)
               (dec current-pow)
               (- remaining (* quotient divisor)))))))

(defn convert
  [input-base num-seq target-base]
  (when (valid-request? input-base num-seq target-base)
    (let [num-seq-as-base-10 (->> num-seq
                                  reverse
                                  (map-indexed #(* %2 (Math/pow input-base %1))))]
      (if (seq num-seq-as-base-10)
        (-> num-seq-as-base-10
            (->> (reduce +))
            long
            (decimal-to-base target-base))
        num-seq-as-base-10))))
