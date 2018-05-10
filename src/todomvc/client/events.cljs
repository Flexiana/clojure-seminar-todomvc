(ns todomvc.client.events
  (:require
    [re-frame.core :as re-frame]))


(re-frame/reg-event-db
  :init-db
  (fn [_ [_ init-db]]
    init-db))
