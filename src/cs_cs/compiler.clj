(ns cs-cs.compiler
  (:require [cljs.closure :as cljs])
  (:import [java.io PushbackReader BufferedReader StringReader]))

(defn forms-seq [^PushbackReader reader]
  (lazy-seq
    (if-let [form (read reader nil nil)]
      (cons form (forms-seq reader)))))

(extend-protocol cljs/Compilable
  String
  (-compile [this opts]
    (let [^PushbackReader reader (-> (StringReader. this) (BufferedReader.) (PushbackReader.))]
      (cljs/compile-form-seq (forms-seq reader)))))

(defn build [source opts]
  (cljs/build source (merge {:optimizations :advanced} opts)))

(comment defn build [source opts]
  (let [opts (merge {:optimizations :advanced} opts) 
        compiled (cljs/-compile source opts)
        js-sources (cljs/add-dependencies opts compiled)]
    (->> js-sources
         (apply cljs/optimize opts)
         (cljs/add-header opts)
         (cljs/output-one-file opts)))) 
