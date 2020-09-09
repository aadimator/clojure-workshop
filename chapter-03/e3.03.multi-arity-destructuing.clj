(def weapon-damage {:fists 10.0 :staff 35.0 :sword 100.0 :cast-iron-saucepan 150.0})

(def enemy {:name "Zulkaz", :health 250, :armor 0.8, :camp :trolls})

(def ally {:name "Carla", :health 80, :camp :gnomes})

(defn strike
  "With one argument, strike a target with a default :fists `weapon`. With two argument, strike a target with `weapon`.
   Strike will heal a target that belongs to the gnomes camp."
  ([target] (strike target :fists))
  ([{:keys [camp armor], :or {armor 0}, :as target} weapon]
   (let [points (weapon weapon-damage)]
     (if (= :gnomes camp)
       (update target :health + points)
       (let [damage (* points (- 1 armor))]
         (update target :health - damage))))))