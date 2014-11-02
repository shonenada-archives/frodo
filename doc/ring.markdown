Intro to Ring 
=============

Ring 规范用 Clojure 数据结构定义了一个标准的数据模式来表示 Web 的请求和响应。
规范请移步：[https://github.com/ring-clojure/ring/blob/master/SPEC](https://github.com/ring-clojure/ring/blob/master/SPEC)


Start with Lein
---------------

使用 lein 创建项目

```shell
lein new frodo
```

编辑 project.clj 添加依赖:

```clojure
(defproject frodo "1.0.0-SNAPSHOT"
  :description "FIXME: write"
  :main frodo.core
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring/ring-core "1.2.2"]
                 [ring/ring-jetty-adapter "1.2.2"]])
```

安装依赖:

```shell
lein deps
```

添加 handler: (src/frodo/core.clj)

```clojure
(ns frodo.core
  (:gen-class)
  (:use [ring.adapter.jetty]))


(defn index-handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hi, Sam"})


(defn drop-ring []
  "Drop the ring into Mount Doom"
  (run-jetty index-handler {:port 3000}))


(defn -main [& args]
  (drop-ring))
```


使用 Compojure 添加 routes
--------------------------

在 project.clj 中添加 dependencies:

```clojure
(defproject frodo "1.0.0-SNAPSHOT"
  :description "FIXME: write"
  :main frodo.core
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring/ring-core "1.2.2"]
                 [ring/ring-jetty-adapter "1.2.2"]
                 [compojure "1.0.1"]])
```

编辑 src/frodo/core.clj:

```clojure
(ns frodo.core
  (:use [ring.adapter.jetty]
        [compojure.core])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]))


(def index []
  {:status 200
   :header {"Content-Type" "text/html"}
   :body "Hi, Sam."})


(defroutes app-routes    ; compojure.core/defroutes 
  (GET "/" [] (index))
  (route/resources "/")
  (route/not-found "Page not Found"))


(defn drop-ring []
  (def app (handler/api app-routes))
  (run-jetty app {:port 3000}))


(def- main [& args]
  (drop-ring))
```

运行服务器:

```shell
lein run
```
