(ns robot-name)

(def digits (map str (range 10)))

(def letters (map char (range 65 91)))

(defn robot
  []
  {:name (atom nil)})

(defn gen-name
  []
  (apply str
         (concat (repeatedly 2 #(rand-nth letters))
                 (repeatedly 3 #(rand-nth digits)))))

(defn uniquify
  [f]
  (let [past-results (atom #{})]
    (fn []
      (loop [possible-result (f)]
        (if-not (@past-results possible-result)
          (do (swap! past-results conj possible-result)
              possible-result)
          (recur (f)))))))

(def gen-unique-name (uniquify gen-name))

(defn reset-name
  [robot]
  (reset! (:name robot) (gen-unique-name))
  robot)

(defn maybe-add-name
  [robot]
  (if (some? @(:name robot))
    robot
    (reset-name robot)))

(defn robot-name
  [robot]
  @(:name (maybe-add-name robot)))
