(ns squeaky-clean
  (:require [clojure.string :as str]))

(defn first-letter-to-upper
  [[first & rest]]
  (str/join (concat [(str/upper-case first)] rest)))

(defn kebab-to-camel
  [string]
  (let [[first & segments] (str/split string #"-")]
    (->> segments
         (map first-letter-to-upper)
         (concat [first])
         str/join)))

(defn clean
  "Create a proper identifier out of input string"
  [s]
  (-> s
      (str/replace #" " "_")
      (str/replace #"[\x{0000}-\x{001F}]|[\x{007F}-\x{009F}]" "CTRL")
      kebab-to-camel
      (str/replace #"[\P{IsAlphabetic}&&[^_]]" "")
      (str/replace #"[\p{InGreek}&&\P{Lu}]" "")))
