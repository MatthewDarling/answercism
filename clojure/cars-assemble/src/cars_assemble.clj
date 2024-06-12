(ns cars-assemble)

(def base-rate
  "Base rate of production, per hour"
  221)

(defn success-ratio-for-speed
  [speed]
  (cond
    (= 0 speed) 0
    (<= 1 speed 4) 1
    (<= 5 speed 8) 0.9
    (= 9 speed) 0.8
    (= 10 speed) 0.77))

(defn production-rate
  "Returns the assembly line's production rate per hour,
   taking into account its success rate"
  [speed]
  (* base-rate speed (success-ratio-for-speed speed)))

(defn working-items
  "Calculates how many working cars are produced per minute"
  [speed]
  (-> speed
      production-rate
      (/ 60)
      Math/floor
      int))
