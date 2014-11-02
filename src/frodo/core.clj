(ns frodo.core
  (:gen-class)
  (:use [ring.adapter.jetty]))


(defn index-handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Frodo."})


(defn drop-ring []
  "Drop the ring into Mount Doom"
  (run-jetty index-handler {:port 3000}))


(defn -main [& args]
  (drop-ring)
)
