(ns todomvc.handler.example
  (:require [compojure.core :refer :all]
            [clojure.java.io :as io]
            [integrant.core :as ig]))

(defmethod ig/init-key :todomvc.handler/example [_ options]
  (context "/example" []
    (GET "/" []
      {:body {:example "data"}}(io/resource "todomvc/handler/example/example.html"))))
