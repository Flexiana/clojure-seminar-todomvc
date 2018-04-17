-- :name get-all :? :*
select * from tasks


-- :name create-task :<! :1
insert into tasks (title, description) values (:title, :description) returning *


-- :name finish-task :<! :1
update tasks set done = true where id = :id returning *
