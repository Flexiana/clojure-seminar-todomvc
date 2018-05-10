(ns todomvc.tasks.api
  (:require
    [struct.core :as st]
    [todomvc.tasks.boundary :as db]
    [todomvc.tasks.schemas :refer [Task]]))


(defn get-tasks
  [conf request]
  {:body (db/get-all (:db conf)), :status 200})


(defn create-task
  [conf request]
  (let [[errors input] (st/validate (:body-params request) Task {:strip true})]
    (if (nil? errors)
      {:body (db/create-task (:db conf) (merge (into {} (map vector (keys Task) (repeat nil)))
                                               input))
       :status 201}
      {:body {:errors errors}, :status 400})))


(defn finish-task
  [conf request]
  (let [[errors input] (st/validate (:params request) {:id [st/required st/integer-str]})
        task (db/finish-task (:db conf) (:id input))]
    (if (nil? task)
      {:status 404}
      {:status 200, :body task})))
