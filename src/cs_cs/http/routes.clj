(ns cs-cs.http.routes
  (:use [compojure.core :only (defroutes GET POST)]
        [cs-cs.http.interface :only (handle-compile)])
  (:require [compojure.handler :as handler])) 

(defroutes routes
  (POST "/compile" [& params] (handle-compile params)))

(def app
  (handler/site routes))
