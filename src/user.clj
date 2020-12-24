(ns user
  (:require
   [dealer-api.core :refer [start-server]]
   [io.pedestal.http :as http]
   ;; Clojure data structures are immutable, they never change unless we use a 
   ;; special data structure called atom
   ;; Our strategy is not to freeze repl but to save the running instance of the server in
   ;; an atom and return from the start function
   [clojure.tools.namespace.repl :refer [refresh]]))

;; For interactive development
(defonce server (atom nil))

;; Start a server and bootstrap it with reset function
(defn go []
  (reset! server (start-server))
  (prn "Server started on localhost:8890")
  (prn "Enter (reset) to reload.")
  :started)

(defn halt []
  (when @server
    (http/stop @server)))

(defn reset []
  (halt)
  (refresh :after 'user/go))