(ns isogram
  (:require [clojure.string :as str]))

(defn isogram?
  [phrase]
  (let [normalized (-> phrase
                       (str/replace #"[ -]" "")
                       str/lower-case)]
    (= (count normalized)
       (count (distinct normalized)))))
