(ns cs-cs.http.interface
  (:require [compojure.handler :as handler]
            [cs-cs.compiler :as comp]
            [clojure.string :as str]))

(defn str-to-bool [str]
  (condp = (str/upper-case str)
    "TRUE" true
    false))

(defn parse-params [params]
  (let [{:keys [cs-code optimizations pretty-print]
               :or {optimizations "advanced"
                    pretty-print "true"}} params]
    [cs-code (keyword optimizations) (str-to-bool pretty-print)]))

(defn call-build [[code opt pp]]
  (try
    [200 (comp/build code {:optimizations opt, :pretty-print pp})]
  (catch Exception e
    [400 (str "caught exception: " (.getMessage e))])))

(defn format-response 
  "Need m0ar functionality" 
  [[status body]]
  {:status status 
   :headers {"Content-Type" "text/plain"}
   :body body})

(defn handle-compile [params]
  (->> params
       parse-params
       call-build
       format-response))
