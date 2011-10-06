(ns cs-cs.http.entry
  (:use [ring.adapter.jetty :only (run-jetty)]
        [cs-cs.http.routes :only (app)]))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (.join (run-jetty (var app) {:port port  
                                 :join? false}))))
