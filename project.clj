(defproject todomvc "0.1.0"
  :description "A Todomvc project for a Clojure seminar"
  :url "https://github.com/Flexiana/clojure-seminar-todomvc"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 [duct/core "0.6.2"]
                 [duct/module.logging "0.3.1"]
                 [duct/module.web "0.6.4"]
                 [duct/module.sql "0.4.2"]
                 [org.clojure/tools.reader "1.2.2"]
                 [org.postgresql/postgresql "42.2.2"]
                 [com.layerware/hugsql "0.4.8"]
                 [ring/ring-json "0.4.0"]
                 [funcool/struct "1.2.0"]
                 [reagent "0.8.0"]
                 [re-frame "0.10.5"]
                 [cljs-ajax "0.7.3"]]
  :plugins [[duct/lein-duct "0.10.6"]
            [lein-cljsbuild "1.1.7"]
            [lein-kibit "0.1.6"]]
  :clean-targets ^{:protect false} ["resources/todomvc/public/js" :target-path]
  :main ^:skip-aot todomvc.main
  :resource-paths ["resources" "target/resources"]
  :prep-tasks ["javac" "compile" ["run" ":duct/compiler"]]
  :profiles
  {:dev [:project/dev :profiles/dev]
   :repl {:prep-tasks ^:replace ["javac" "compile"]
          :repl-options {:init-ns user}}
   :uberjar {:aot :all}
   :profiles/dev {}
   :project/dev  {:source-paths ["dev/src"]
                  :resource-paths ["dev/resources"]
                  :dependencies [[integrant/repl "0.3.1"]
                                 [eftest "0.5.2"]
                                 [kerodon "0.9.0"]
                                 [figwheel "0.5.16"]
                                 [figwheel-sidecar "0.5.16"]
                                 [re-frisk "0.5.4"]
                                 [binaryage/devtools "0.9.10"]]
                  :plugins [[lein-figwheel "0.5.16"]
                            [venantius/ultra "0.5.2"]]}}
  :cljsbuild
  {:builds
   [{:id "dev"
     :source-paths ["src" "dev/src"]
     :compiler {:main dev.client
                :output-to "resources/todomvc/public/js/main.js"
                :output-dir "resources/todomvc/public/js/out"
                :asset-path "js/out"
                :source-map-timestamp true
                :preloads [devtools.preload]
                :external-config {:devtools/config {:features-to-install :all}}}}

    {:id "min"
     :source-paths ["src"]
     :compiler {:main todomvc.client.main
                :output-to "resources/todomvc/public/js/main.js"
                :output-dir "resources/todomvc/public/js/out-min"
                :optimizations :advanced
                :closure-defines {goog.DEBUG false}
                :pretty-print false
                :parallel-build true}
     :warning-handlers
     [(fn [warning-type env extra]
        (when (warning-type cljs.analyzer/*cljs-warnings*)
          (when-let [s (cljs.analyzer/error-message warning-type extra)]
            (binding [*out* *err*]
              (println "WARNING:" (cljs.analyzer/message env s)))
            (System/exit 1))))]}]}

  :aliases {"min-app" ["do" "clean," "cljsbuild" "once" "min"]})
