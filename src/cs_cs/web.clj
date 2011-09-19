(ns cs-cs.web
  (:use [ring.adapter.jetty :only (run-jetty)]
        [compojure.core :only (defroutes GET)])
  (:require [compojure.handler :as handler]))

;(defn render-image
;  "Returns the proper Ring response for an image"
;  [bytes]
;  {:status 200
;  :headers {"Content-Type" "image/png"}
;  :body bytes})


;(defn create-chart []
;  (-> (twitter/fetch-public-timeline)
;      (metrics/words)
;      (metrics/freqs)
;      (metrics/word-chart)
;      (chart->bytes)
;      (render-image)))

(defroutes routes
  (GET "/" [] "hello"))

(def app
  (handler/site routes))

(defn start [port]
  (run-jetty (var app) {:port port  
                                :join? false}))
(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (.join (start port))))
