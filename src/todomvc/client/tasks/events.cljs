(ns todomvc.client.tasks.events
  (:require
    [re-frame.core :as re-frame]
    [todomvc.client.tasks.middlewares :as m]))


(re-frame/reg-event-db
  :tasks/get-tasks
  [m/get-tasks]
  (fn [db _]
    db))


(re-frame/reg-event-db
  :tasks/handle-get-tasks
  (fn [db [_ tasks]]
    (assoc-in db [:tasks :list] tasks)))


(re-frame/reg-event-db
  :tasks/handle-error-get-tasks
  (fn [db [_ xhr]]
    db))


(re-frame/reg-event-db
  :tasks/update-new-task
  (fn [db [_ title]]
    (assoc-in db [:tasks :new-task] title)))


(re-frame/reg-event-db
  :tasks/create-task
  [m/create-task]
  (fn [db _]
    db))


(re-frame/reg-event-db
  :tasks/handle-create-task
  [m/get-tasks]
  (fn [db _]
    (assoc-in db [:tasks :new-task] nil)))


(re-frame/reg-event-db
  :tasks/handle-error-create-task
  (fn [db [_ response]]
    db))
