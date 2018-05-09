(ns todomvc.handler.api
  (:require
    [compojure.core :refer [context GET POST PATCH]]
    [integrant.core :as ig]
    [ring.middleware.json :refer [wrap-json-response]]
    [todomvc.tasks.api :as tasks]))


(defmethod ig/init-key :todomvc.handler/api
  [_ conf]
  (wrap-json-response
    (context "/api" []
      (context "/tasks" []
        (GET "/" [:as request]
          (tasks/get-tasks conf request))
        (POST "/" [:as request]
          (tasks/create-task conf request))
        (PATCH "/:id" [id :as request]
          (tasks/finish-task conf request))))))
