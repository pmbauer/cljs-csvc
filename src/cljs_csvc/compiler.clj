(ns cljs-csvc.compiler
  (:require [cljs.closure :as cljs])
  (:require [cljs.compiler :as comp])
  (:import [java.io PushbackReader BufferedReader StringReader]
           [clojure.lang PersistentArrayMap]))

(defn forms-seq [^PushbackReader reader]
  (lazy-seq
    (if-let [form (read reader nil nil)]
      (cons form (forms-seq reader)))))

(extend-protocol cljs/Compilable
  String
  (-compile [this opts]
    (let [^PushbackReader reader (-> (StringReader. this)
                                     (BufferedReader.)
                                     (PushbackReader.))]
      (cljs/compile-form-seq (forms-seq reader))))
  
  PersistentArrayMap
  (-compile [this opts]
    (cljs/-compile (:tempfile this) opts)))

(defn build [source opts]
  (binding [comp/namespaces (atom @comp/namespaces)]
    (cljs/build source (merge {:optimizations :advanced} opts))))


;(defn build [source opts]
;  (let [opts (merge {:optimizations :advanced} opts) 
;        compiled (cljs/-compile source opts)
;        js-sources (cljs/add-dependencies opts compiled)]
;    (->> js-sources
;         (apply cljs/optimize opts)
;         (cljs/add-header opts)
;         (cljs/output-one-file opts)))) 
