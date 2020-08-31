(def base-co2 382)
(def base-year 2006)

(defn co2-estimate
  "Estimates the co2 emission"
  [year]
  (let [year-diff (- year base-year)]
    (+ 382 (* year-diff 2))))

(co2-estimate 2050)