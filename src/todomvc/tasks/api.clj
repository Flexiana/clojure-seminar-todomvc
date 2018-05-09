(ns todomvc.tasks.api
  (:require
    [struct.core :as st]
    [todomvc.tasks.boundary :as db]))


(defn get-tasks
  [conf request]
  {:body (db/get-all (:db conf)), :status 200})


(def Task
  {:title [st/required]
   :description [st/string]})


(defn create-task
  [conf request]
  (let [[errors input] (st/validate (:body-params request) Task {:strip true})]
    (if (nil? errors)
      {:body (db/create-task (:db conf) (merge (into {} (map vector (keys Task) (repeat nil)))
                                               input))
       :status 201}
      {:body {:errors errors}, :status 400})))
