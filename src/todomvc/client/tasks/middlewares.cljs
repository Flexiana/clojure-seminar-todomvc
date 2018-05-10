(ns todomvc.client.tasks.middlewares
  (:require
    [ajax.core :refer [GET POST]]
    [re-frame.core :as re-frame]))


(def get-tasks
  (re-frame/after
    (fn [db _]
      (GET "/api/tasks"
           :format :json
           :response-format :json
           :keywords? true
           :handler #(re-frame/dispatch [:tasks/handle-get-tasks %])
           :error-handler #(re-frame/dispatch [:tasks/handle-error-get-tasks %])))))


(def create-task
  (re-frame/after
    (fn [db _]
      (when (-> db :tasks :errors nil?)
        (POST "/api/tasks"
              :params {:title (-> db :tasks :new-task)}
              :format :json
              :response-format :json
              :keywords? true
              :handler #(re-frame/dispatch [:tasks/handle-create-task %])
              :error-handler #(re-frame/dispatch [:tasks/handle-error-create-task %]))))))
