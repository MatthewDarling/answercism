(ns all-your-base)

(defn valid-request?
  [input-base num-seq target-base]
  (and (every? (complement neg?) num-seq) ;; Positive digits only
       (every? #(< % input-base) num-seq) ;; Digits can't be larger than base
       ;; No unary numeral system support
       (>= input-base 2)
       (>= target-base 2)))

(defn reduce-helper
  "Rather than do a `loop`+`recur`, I thought this would read fairly nicely as a
  `reduce`, using `reduced` to end when we hit the base case. Except the base
  case is a separate variable from the output, so we accumulate with a map. And
  we don't have a sequence to iterate over, but we _do_ have another variable we
  need for our division and modulus. So we `reduce` across an infinite sequence
  of `target-base`. It's a bit too clever, but it's easy to follow with an
  explanation."
  [{:keys [output remaining]} target-base]
  (if (zero? remaining)
    (reduced output)
    {:output (conj output (mod remaining target-base))
     :remaining (int (/ remaining target-base))}))

(defn convert
  [input-base num-seq target-base]
  (when (valid-request? input-base num-seq target-base)
    ;; Exercism recommends "Positional Notation" where you multiply the digits
    ;; by successive powers of input-base. However, this can be factored out
    ;; into addition of the digits and repeated multiplications of the base
    ;; Taken from the Python community solutions, thank you!
    (let [as-base-10 (reduce #(-> %1 (* input-base) (+ %2))
                             0
                             num-seq)]
      (if (zero? as-base-10)
        (if (seq num-seq) [0] [])
        ;; Iterate over an infinite sequence of `target-base`, with repeated
        ;; modulus and division operations to convert from base10
        ;;
        ;; Using a list as the accumulator because each successive modulus
        ;; represents a bigger piece of the value, so we want to add at the
        ;; front
        (->> target-base
             repeat
             (reduce reduce-helper
                     {:output '()
                      :remaining as-base-10})
             seq)))))
