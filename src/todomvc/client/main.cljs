(ns todomvc.client.main
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]
    [todomvc.client.tasks.components :as components]))


(defn- main
  []
  (components/layout))


(defn ^:export init
  []
  (let [app-element (.getElementById js/document "app")]
    (re-frame/clear-subscription-cache!)
    (reagent/render [main] app-element)))
