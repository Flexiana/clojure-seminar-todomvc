(ns todomvc.handler.site
  (:require
    [compojure.core :refer [GET]]
    [integrant.core :as ig]
    [ring.util.response :as response]))


(defmethod ig/init-key :todomvc.handler/site
  [_ conf]
  (GET "/" [:as request]
    (response/content-type
      (response/resource-response "todomvc/pages/index.html")
      "text/html")))
