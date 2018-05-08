(ns todomvc.tasks.api
  (:require
    [todomvc.tasks.boundary :as db]))


(defn get-tasks
  [conf request]
  {:body (db/get-all (:db conf)), :status 200})
