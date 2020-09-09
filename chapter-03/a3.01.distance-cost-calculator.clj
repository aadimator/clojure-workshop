(def walking-speed 5)
(def driving-speed 70)

(def paris {:lat 48.856483 :lon 2.352413})
(def bordeaux {:lat 44.834999  :lon -0.575490})

(def vehicle-cost-fns
  {:sporche (partial * 0.12 1.5)
   :tayato (partial * 0.07 1.5)
   :sleta (partial * 0.2 0.1)})

(defn distance
  [{{lat1 :lat, lon1 :lon} :from
    {lat2 :lat, lon2 :lon} :to}]
  (let [cos (Math/cos lat1)
        lat-sqr (Math/pow (- lat2 lat1) 2)
        lon-sqr (Math/pow (- lon2 lon1) 2)]
    (* 110.25 (Math/sqrt (+ lat-sqr (* cos lon-sqr))))))

(distance {:from paris, :to bordeaux})

(defmulti itinerary :transport)

(defmethod itinerary :walking
  [{:keys [from to]}]
  (let [dist (distance {:from from :to to})
        duration (/ dist walking-speed)
        cost 0]
    {:cost cost, :distance dist, :duration duration}))

(itinerary {:from paris :to bordeaux :transport :walking})

(defmethod itinerary :driving
  [{:keys [from to vehicle]}]
  (let [dist (distance {:from from :to to})
        duration (/ dist walking-speed)
        cost ((vehicle vehicle-cost-fns) dist)]
    {:cost cost, :distance dist, :duration duration}))

(itinerary {:from paris :to bordeaux :transport :driving :vehicle :tayato})