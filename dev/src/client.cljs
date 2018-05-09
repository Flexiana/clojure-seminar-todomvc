(ns dev.client
  (:require
    [figwheel.client :as fw]
    [re-frisk.core :refer [enable-re-frisk!]]
    [todomvc.client.main]))

(enable-console-print!)
(enable-re-frisk!)


(fw/start {:on-jsload todomvc.client.main/init
           :websocket-url "ws://localhost:3449/figwheel-ws"})
