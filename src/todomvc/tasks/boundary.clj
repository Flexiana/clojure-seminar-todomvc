(ns todomvc.tasks.boundary
  (:require
    [duct.database.sql]
    [todomvc.tasks.db :as db]))


(defprotocol TaskService
  (get-all [db-spec]))


(extend-protocol TaskService
  duct.database.sql.Boundary
  (get-all [db-spec]
    (db/get-all (:spec db-spec))))
