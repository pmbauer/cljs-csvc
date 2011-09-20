(ns cs-cs.web
  (:use [ring.adapter.jetty :only (run-jetty)]
        [compojure.core :only (defroutes GET POST)])
  (:require [compojure.handler :as handler]
            [cs-cs.compiler :as comp]
            [clojure.string :as str]))

(defn str-to-bool [str]
  (condp = (str/upper-case str)
    "TRUE" true
    false))

(defn compile-cs
  "Need m0ar functionality" 
  [code opt-str pp-str]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (comp/build code {:optimizations (keyword opt-str)
                           :pretty-print  (str-to-bool pp-str)})})

(defroutes routes
  (POST "/compile" [& params]
    (let [{:keys [cs-code optimizations pretty-print]
                 :or {optimizations "advanced"
                      pretty-print "true"}} params]
      (compile-cs cs-code optimizations pretty-print))))

(def app
  (handler/site routes))

(defn start [port]
  (run-jetty (var app) {:port port  
                        :join? false}))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (.join (start port))))
