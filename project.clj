(defproject org.clojars.scij/melee "0.2.0-SNAPSHOT"
  :description "Distributed coordinator"
  :url "https://github.com/scij/melee"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [com.google.guava/guava "23.0"]]
  :profiles {:dev { :dependencies [[midje "1.9.9"]]
                    :plugins [[lein-midje "3.2.2"]]}}
  )
