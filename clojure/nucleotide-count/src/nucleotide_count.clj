(ns nucleotide-count)

(def valid-dna-characters #{\A \C \G \T})

(defn count-of-nucleotide-in-strand
  [nucleotide strand]
  (if-not (valid-dna-characters nucleotide)
    (throw (ex-info "Invalid nucleotide choice" {:input nucleotide}))
    (-> nucleotide
        str
        re-pattern
        (re-seq strand)
        count)))

(defn nucleotide-counts
  [strand]
  (if (re-find #"[^ACGT]" strand)
    (throw (ex-info "Invalid DNA strand" {:input strand}))
    (zipmap valid-dna-characters
            (map #(count-of-nucleotide-in-strand % strand) valid-dna-characters))))
