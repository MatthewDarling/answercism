(ns pig-latin
  (:require [clojure.string :as str]))

(def add-ay-pattern
  "Starts with vowel, or starts with xr/yt"
  #"^([aeiou]|xr|yt)")

(def qu-start-pattern
  "Starts with qu, including with consonants before"
  #"^([^aeiou]*qu)(.*)")

(def y-start-pattern
  "Starts with y after consonants"
  #"^([^aeiou]+)(y.*)")

(def normal-consonant-pattern
  "Starts with consonant, no y/qu special case"
  #"^([^aeiou]+)(.*)")

(defn re-replace-if-match
  [input pattern]
  (when (re-find pattern input)
    (str/replace input pattern "$2$1ay")))

(defn translate-one-word
  [word]
  (cond
    (re-find add-ay-pattern word) (str word "ay")
    :else (->> [qu-start-pattern
                y-start-pattern
                normal-consonant-pattern]
               (keep #(re-replace-if-match word %))
               first)))

(defn translate
  [input]
  (-> input
      (str/split #"\s")
      (->> (map translate-one-word)
           (str/join " "))))
