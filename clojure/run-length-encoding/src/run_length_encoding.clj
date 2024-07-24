(ns run-length-encoding
  (:require [clojure.string :as str]))

(defn encode-vector
  "If `char-vector` has multiple characters, use a preceding number to represent
  the repetition."
  [char-vector]
  (if (= 1 (count char-vector))
    (str (first char-vector))
    (str (count char-vector) (first char-vector))))

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (->> plain-text
       (partition-by identity)
       (map encode-vector)
       (apply str)))

(defn multiply-char
  "If `rle-piece` contains a number before the character, repeat the character.
  Otherwise return the single character."
  [rle-piece]
  (if (= 1 (count rle-piece))
    rle-piece
    (let [counter (re-find #"^\d+" rle-piece)]
      (apply str (repeat (parse-long counter) (last rle-piece))))))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [cipher-text]
  (let [pieces (re-seq #"\d*\D" cipher-text)]
    (apply str (mapcat multiply-char pieces))))
