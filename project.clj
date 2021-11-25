(defproject jme-clj-earth "0.1.0-SNAPSHOT"
  :dependencies [[jme-clj "0.1.13"]
                 [org.clojure/clojure "1.10.3"]]
  :description "An attempt to model planet Earth in jme-clj"
  :license {:name "GNU General Public License,version 2.0 or (at your option) any later version"
            :url  "https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html"}
  :main ^:skip-aot jme-clj-earth.core
  :profiles {:uberjar {:aot      :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}}
  :repl-options {:init-ns jme-clj-earth.core}
  :source-paths      ["src"]
  :url "http://example.com/FIXME")
