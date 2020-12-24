(ns dealer-api.drugs
  (:require [clojure.spec.alpha :as s]
            [dealer-api.sql.drugs :as sql]
            [dealer-api.config :refer [db]]
            [io.pedestal.http :as http]))

;; we created a handler all-drugs and used SQL definitions defined using a macro
(defn all-drugs [_]
  ;; http/json-response converts a map to a valid response with all the required keys
  ;; the _ argument to all-drugs function means that one argument will be passed to the function but we do not care
  (http/json-response (sql/drugs db)))

(defn drug-by-id [request])

(s/def ::name string?)
(s/def ::availability int?)
(s/def ::price (s/or :price int?
                     :price float?))

(s/def ::drug (s/keys :req-un [::name ::availability ::price]))

(defn create-drug [request]
  (let [new-drug (select-keys (-> request :json-params)
                              [:name :availability :price])]
    (if (s/valid? ::drug new-drug)
      (let [[_ id] (sql/new-drug db new-drug)]
        (http/json-response
         {:msg "Drug created successfully."
          :id id}))
      (assoc (http/json-response
              {:msg "Please send a valid drug."})
             :status 400))))

(defn update-drug-by-id [request])

(defn delete-drug [request])