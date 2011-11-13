(ns cljs-csvc.http.entry
  (:use [ring.adapter.jetty :only (run-jetty)]
        [cljs-csvc.http.routes :only (app)]))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (.join (run-jetty (var app) {:port port  
                                 :join? false}))))
