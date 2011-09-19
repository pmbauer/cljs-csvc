(defproject strangeloop "1.0.0-SNAPSHOT"
  :description "Clojure analytics workshop example"
  :dependencies [[org.clojure/clojure "1.3.0-RC0"]
                 [org.clojure/data.json "0.1.1"]
                 [compojure "0.6.5"]
                 [ring/ring-jetty-adapter "0.3.11"]]
  :dev-dependencies [[swank-clojure "1.4.0-SNAPSHOT" :exclusions [org.clojure/clojure]]])
