(ns wordy
  (:require [instaparse.core :as insta]
            [clojure.string :as str]
            [clojure.set :as set]))

(def my-parser
  "'What is' means that we're looking for that literal string, it's the only valid
  way to open the question. '?' needs to be at the end, and we have a few
  supported mathematical operators. All of those are literals.

  <> on the left-hand side means it's kept in the output but not tagged. We only
  need the supported math operators + numbers, so everything else in the
  question string can be discarded, but left-side <> doesn't do that.

  Instead, <> on the right-hand side means don't include in the parsed output at
  all, which is what we want for the English structuring for the question.

  Negative numbers are supported, so check for a - in front of the digits.

  The question can be either a literal number, or an operation, which itself can
  be made of nested operations. We can consider '5 plus 5 plus 5' to be made of
  two operations. Since we're expected to process operations from right to left,
  we define operations as being recursive at the left."
  (insta/parser
   "question = <'What is'> <whitespace> (operation | number) <'?'>
    operation = (operation | number) <whitespace> operator <whitespace> number
    whitespace = #'\\s+'
    number = #'-?[0-9]+'
    operator = 'plus' | 'minus' | 'multiplied by' | 'divided by'"))

(def operator-fns
  {"plus" +
   "minus" -
   "multiplied by" *
   "divided by" /})

(def valid-words
  "The question syntax is quite rigid, so we can report an unsupported operator if
  we see any words not in this set."
  (set/union #{"what" "is"}
             (->> operator-fns
                  keys
                  (mapcat #(str/split % #" "))
                  set)))

(defn evaluate-op
  [x operator y]
  (operator x y))

(defn valid-question?
  [question]
  (->> question
       (re-seq #"\p{Alpha}+")
       (map str/lower-case)
       (every? valid-words)))

(defn parse-question
  [question]
  (let [parsed (my-parser question)]
    (if (insta/failure? parsed)
      (throw (IllegalArgumentException. "syntax error"))
      parsed)))

(defn evaluate
  "Parse the plaintext question, rejecting any random questions before sending
  them for parsing. Operations are processed from left to right, which falls out
  naturally from `insta/transform` and the rules being defined with left-hand
  recursion. If proper order of operations was required, I think the grammar
  definition could stay the same, but we'd have to process the output quite
  differently."
  [question]
  (if-not (valid-question? question)
    (throw (IllegalArgumentException. "unknown operation"))
    (->> question
         parse-question
         (insta/transform {:number parse-long
                           :operator operator-fns
                           :operation evaluate-op
                           :question identity}))))
