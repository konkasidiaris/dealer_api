;; src/dealer_api/core.clj

(ns dealer-api.core
  (:require [io.pedestal.http :as http]
            ;; Clojure data structures are immutable, they never change unless we use a 
            ;; special data structure called atom
            ;; Our strategy is not to freeze repl but to save the running instance of the server in
            ;; an atom and return from the start function
            [clojure.tools.namespace.repl :refer [refresh]]))

;; this is the controlling function
(defn respond-hello [request]
  (println request " The request ")
  {:status 200
   :body "Hello World"})

;; this is the routing function
(def routes
  #{["/hello" :get `respond-hello]})

;; todo add comments when you understand this part
(def service-map
  {::http/routes routes
   ::http/type   :jetty
   ::http/port   8890})

;; For interactive development
(defonce server (atom nil))

;; Start a server and bootstrap it with reset function
(defn go []
  (reset! server
          (http/start (http/create-server
                       (assoc service-map
                              ::http/join? false))))
  (prn "Server started on localhost:8890")
  (prn "Enter (reset) to reload.")
  :started)

;; Stop a server
(defn halt []
  (http/stop @server))

;; Stop and start again server using go function
(defn reset []
  (halt)
  (refresh :after 'dealer-api.core/go))