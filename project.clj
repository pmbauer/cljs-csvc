(defproject cs-cs "1.0.0-SNAPSHOT"
  :description "Clojure analytics workshop example"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/data.json "0.1.1"]
                 [compojure "0.6.5"]
                 [org.clojars.pmbauer/clojurescript_cs "1.0.0-SNAPSHOT"]
                 [org.clojars.pmbauer/goog.js "1.0.0-SNAPSHOT"]
                 [org.clojars.pmbauer/goog.compiler "1.0.0-SNAPSHOT"]
                 [ring/ring-jetty-adapter "0.3.11"]]
  :dev-dependencies [[swank-clojure "1.4.0-SNAPSHOT" :exclusions [org.clojure/clojure]]
                     [slamhound "1.2.0"]])
