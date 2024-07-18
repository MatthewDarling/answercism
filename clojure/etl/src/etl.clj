(ns etl
  (:require [clojure.string :as str]))

(defn transform
  [source]
  (apply hash-map (mapcat #(interleave (map str/lower-case (val %))
                                       (repeat (key %)))
                          source)))
