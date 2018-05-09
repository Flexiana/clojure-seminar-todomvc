(ns todomvc.tasks.api
  (:require
    [todomvc.tasks.boundary :as db]))


(defn get-tasks
  [conf request]
  {:body (db/get-all (:db conf)), :status 200})


(defn create-task
  [conf request]
  (let [new-task (db/create-task (:db conf) (:body-params request))]
    {:body new-task, :status 201}))
