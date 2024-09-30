(ns atbash-cipher
  (:require [clojure.string :as str]))

(def cipher
  (let [alphabet (map char (range (int \a) (inc (int \z))))]
    (zipmap alphabet (reverse alphabet))))

(defn encode
  [text]
  (let [ciphered (-> text
                     str/lower-case
                     (str/replace #"\W" "")
                     (->> (map (some-fn cipher identity))))]
    (->> ciphered
         (partition-all 5)
         (map #(apply str %))
         (str/join " "))))

(defn decode
  "Decode the space-separated ciphertext into unseparated cleartext. The Python
  version of this problem asks for this, but the Clojure version doesn't, so I
  thought I'd throw it in anyway."
  [text]
  (-> text
      (str/replace " " "")
      (->> (map (some-fn cipher identity))
           (str/join ""))))
