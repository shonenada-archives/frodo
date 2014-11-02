(ns frodo.core
  (:gen-class)
  (:use [ring.adapter.jetty]
        [compojure.core])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [frodo.views :as views]))


(defroutes app-routes 
  (GET "/" request (views/index request))
  (GET "/say-hi/:username" [username] (views/say-hi username))
  (route/resources "/")
  (route/not-found (views/page-not-found)))


(defn drop-ring []
  "Drop the ring into Mount Doom"
  (def app (handler/api app-routes))
  (run-jetty app {:port 3000}))


(defn -main [& args]
  (drop-ring))
