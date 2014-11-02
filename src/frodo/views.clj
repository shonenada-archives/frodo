(ns frodo.views)


(defn index []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Frodo."})
