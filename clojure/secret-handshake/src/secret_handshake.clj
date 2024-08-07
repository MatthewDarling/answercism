(ns secret-handshake)

(def commands-mask ["wink" "double blink" "close your eyes" "jump" :reverse])

(defn ->binary
  [num]
  (when (integer? num)
    (map (comp parse-long str)
         (Integer/toBinaryString num))))

(defn maybe-reverse
  [command-seq]
  (if (= :reverse (last command-seq))
    (reverse (drop-last command-seq))
    command-seq))

(defn commands
  [num]
  (let [binary-seq (->binary num)
        ;;; numbers >31 are accepted, but only the lowest 5 bits matter
        important-binary-bits (take 5 (reverse binary-seq))]
    (->> important-binary-bits
          ;; keep doesn't accept multiple seqs
         (map #(when (not (zero? %2)) %1)
              commands-mask)
         (remove nil?)
         maybe-reverse)))
