(ns todomvc.handler.example
  (:require
    [compojure.core :refer [context GET routes]]
    [clojure.java.io :as io]
    [integrant.core :as ig]
    [ring.util.response :as response]))


(defn handle-example
  [conf]
  (context "/example" [:as request]
    (GET "/" []
      (io/resource "todomvc/handler/example/example.html"))))


(defn handle-hello
  [conf]
  (context "/hello/:url-param" [url-param query-param :as request]
    (GET "/" []
      (response/response (format "<h1>Hello %s, :param %s, :query-param %s</h1>" (:name conf) url-param query-param)))))


(defn wrap-content-type
  [handler content-type]
  (fn [request]
    (-> request
        handler
        (assoc-in [:headers "Content-Type"] content-type))))


(defn app
  [conf]
  (routes
    (handle-example conf)
    (handle-hello conf)))


(defmethod ig/init-key :todomvc.handler/example
  [_ conf]
  (-> conf
      app
      (wrap-content-type "text/html")))
