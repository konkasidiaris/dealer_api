;; src/dealer_api/core.clj

(ns dealer-api.core
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.body-params :refer [body-params]]
            [io.pedestal.http.route :as route]
            [dealer-api.drugs]))

;; this is the controlling function
(defn respond-hello [request]
  {:status 200
   :body "Hello World"})

;; this is the routing function
(def routes
  #{["/hello" :get `respond-hello]
    ;; the route-name parameter lets us create named routes
    ["/drugs" :get dealer-api.drugs/all-drugs :route-name :get-drugs]
    ["/drugs" :post [(body-params) dealer-api.drugs/create-drug] :route-name :post-drugs]})

;; todo add comments when you understand this part
(def service-map
  {::http/routes routes
   ::http/type   :jetty
   ::http/port   8890})

(defn start-server []
  (http/start (http/create-server
               (assoc service-map
                      ::http/join? false))))