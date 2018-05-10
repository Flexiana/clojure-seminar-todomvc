(ns todomvc.tasks.schemas
  (:require
    [struct.core :as st]))


(def Task
  {:title [st/required]
   :description [st/string]})
