(ns matching-brackets
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(def chars-to-remove 
  "Remove any character that isn't a paren/bracket/brace."
  #"[^()\[\]{}]")
(def opening-chars #{\[ \( \{})
(def closing-pairs
  {\] \[
   \} \{
   \) \(})

(defn manage-stack
  "Use with `reduce`. Using a vector as `stack`, add `opening-chars` to `stack`, and pop matching closing chars from `stack`. If any unmatching closing chars are found, exit the reduce early."
  [stack char]
  (if (opening-chars char)
    (conj stack char)
    (if (= (closing-pairs char) (peek stack))
      (pop stack)
      (reduced false))))

(defn valid? 
  [input-string]
  (let [relevant-chars (str/replace input-string chars-to-remove "")
        stack (reduce manage-stack [] relevant-chars)]
    (and (not (boolean? stack)) ;; Got the reduced output; it gets unwrapped when reduce returns, so reduced? doesn't work
         (not (seq stack)))))