(ns todomvc.tasks.api-test
  (:require
    [clojure.test :refer [deftest is]]
    [cheshire.core :as cheshire]
    [integrant.core :as ig]
    [ring.mock.request :as mock]
    [todomvc.tasks.api]
    [todomvc.tasks.boundary :refer [TaskService]]))


(defn json-response
  [conf request]
  (let [handler (ig/init-key :todomvc.handler/api conf)]
    (-> request
        handler
        (update :body #(some-> % (cheshire/decode true))))))


(deftest get-tasks-test
  (let [conf {:db (reify TaskService
                    (get-all [db-spec]
                      []))}
        request (mock/request :get "/api/tasks")
        response (json-response conf request)]
    (is (= 200 (:status response)))
    (is (= [] (:body response)))))
