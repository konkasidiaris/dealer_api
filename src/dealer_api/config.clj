(ns dealer-api.config)

;; the good practice here would be to use a .edn file rather than a config.clj
(def db
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname "//localhost:5432/dealer_dev"
   :user "postgres"
   :password "password"})