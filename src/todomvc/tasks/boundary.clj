(ns todomvc.tasks.boundary
  (:require
    [duct.database.sql]
    [todomvc.tasks.db :as db]))


(defprotocol TaskService
  (get-all [db-spec]))


(extend-protocol TaskService
  duct.database.sql.Boundary
  (get-all [db-spec]
    (db/get-all (:spec db-spec)))

  (create-task [db-spec task]
    (db/create-task (:spec db-spec) task))

  (finish-task [db-spec id]
    (db/finish-task (:spec db-spec) {:id id})))
