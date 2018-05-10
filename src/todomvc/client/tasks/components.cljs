(ns todomvc.client.tasks.components
  (:require
    [goog.events.KeyCodes :as KeyCodes]
    [re-frame.core :as re-frame]))


(defn todo-list
  [tasks]
  [:ul.todo-list
   (for [task tasks]
     ^{:key (:id task)}
     [:li
      [:div.view
       [:input {:type "checkbox", :class "toggle"}]
       [:label (:title task)]
       [:button.destroy]]
      [:input {:type "text", :class "edit", :id (str "task-" (:id tasks))}]])])


(defn main
  [tasks]
  [:section.main
   [:input {:type "checkbox"
            :name "toggle"
            :class "toggle-all"
            :id "toggle-all"}]
   [:label {:for "toggle-all"} "Mark all as complete"]
   [todo-list tasks]])


(defn todomvc-wrapper
  [db]
  (let [tasks (-> db :tasks :list)
        new-task (-> db :tasks :new-task)]
    [:div.todomvc-wrapper
     [:section.todoapp
      [:header.header
       [:h1 "todos"]
       [:input {:class "new-todo"
                :placeholder "What needs to be done?"
                :auto-focus true
                :value new-task
                :on-key-up #(when (= KeyCodes/ENTER (.-keyCode %))
                              (re-frame/dispatch [:tasks/create-task]))
                :on-change #(re-frame/dispatch [:tasks/update-new-task (-> % .-target .-value)])}]]
      (when (seq tasks)
        [main tasks])]]))
