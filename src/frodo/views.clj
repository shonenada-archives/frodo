(ns frodo.views)


(defn index [request]
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (str "Frodo, from ." (:remote-addr request))})


(defn say-hi [username]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "Hi, " username)})


(defn page-not-found []
  "Page not Found.")
