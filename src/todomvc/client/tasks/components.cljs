(ns todomvc.client.tasks.components)


(defn layout
  []
  [:div.todomvc-wrapper
   [:section.todoapp
    [:header.header
     [:h1 "todos"]
     [:input {:class "new-todo"
              :placeholder "What needs to be done?"
              :auto-focus true}]]]])
