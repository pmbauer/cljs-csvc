(ns cljs-csvc.http.routes
  (:use [compojure.core :only (defroutes GET POST)]
        [cljs-csvc.http.interface :only (handle-compile)])
  (:require [compojure.handler :as handler])) 

(defroutes routes
  (POST "/compile" [& params] (handle-compile params)))

(def app
  (handler/site routes))
