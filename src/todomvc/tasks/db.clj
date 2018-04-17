(ns todomvc.tasks.db
  (:require
    [hugsql.core :as hugsql]))


(hugsql/def-db-fns "todomvc/tasks/db.sql" {:quoting :ansi})
(hugsql/def-sqlvec-fns "todomvc/tasks/db.sql" {:quoting :ansi})
