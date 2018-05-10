(ns todomvc.client.main
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]
    [todomvc.client.events]
    [todomvc.client.subs]
    [todomvc.client.tasks.components :as components]
    [todomvc.client.tasks.events]))


(def init-db
  {:tasks {:list nil
           :new-task nil
           :errors nil}})


(defn- main
  []
  (reagent/create-class
    {:display-name
     "main"

     :component-will-mount
     #(re-frame/dispatch [:tasks/get-tasks])

     :reagent-render
     (fn []
       (let [app (re-frame/subscribe [:app])]
         (fn []
           (components/todomvc-wrapper @app))))}))


(defn ^:export init
  []
  (let [app-element (.getElementById js/document "app")]
    (re-frame/dispatch-sync [:init-db init-db])
    (re-frame/clear-subscription-cache!)
    (reagent/render [main] app-element)))
