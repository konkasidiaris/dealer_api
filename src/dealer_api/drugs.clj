(ns dealer-api.drugs
  (:require [dealer-api.sql.drugs :as sql]
            [dealer-api.config :refer [db]]
            [io.pedestal.http :as http]))

;; we created a handler all-drugs and used SQL definitions defined using a macro
(defn all-drugs [_]
  ;; http/json-response converts a map to a valid response with all the required keys
  ;; the _ argument to all-drugs function means that one argument will be passed to the function but we do not care
  (http/json-response (sql/drugs db)))