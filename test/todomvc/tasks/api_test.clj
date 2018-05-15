(ns todomvc.tasks.api-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [cheshire.core :as cheshire]
    [integrant.core :as ig]
    [ring.mock.request :as mock]
    [todomvc.handler.api]
    [todomvc.tasks.boundary :refer [TaskService]]))


(defn json-response
  ([conf method path]
   (json-response conf method path nil))
  ([conf method path body]
   (let [handler (ig/init-key :todomvc.handler/api conf)
         request (mock/request method path)]
     (-> (if (nil? body)
           request
           (assoc request :body-params body))
         handler
         (update :body #(some-> % (cheshire/decode true)))))))


(deftest get-tasks-test
  (let [conf {:db (reify TaskService
                    (get-all [db-spec]
                      []))}
        response (json-response conf :get "/api/tasks")]
    (is (= 200 (:status response)))
    (is (= [] (:body response)))))


(deftest create-task-test
  (testing "should create a task"
    (let [conf {:db (reify TaskService
                      (create-task [db-spec params]
                        (assoc params :id 1)))}
          response (json-response conf :post "/api/tasks" {:title "Task 1"})]
      (is (-> response :body :id some?))
      (is (= 201 (:status response)))))

  (testing "should return bad request"
    (let [response (json-response {:db nil} :post "/api/tasks" {})]
      (is (= {:errors {:title "this field is mandatory"}} (:body response)))
      (is (= 400 (:status response))))))
