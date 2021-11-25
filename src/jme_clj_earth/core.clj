(ns jme-clj-earth.core
  "Display a model of Earth (doesn't work yet)"
  (:require
   [jme-clj.core :refer [add-light-to-root add-to-root defsimpleapp geo light material set* sphere start vec3]])
  (:import
   (com.jme3.math ColorRGBA))
  (:gen-class))


(defn init []
  (let [earth         (sphere 500, 1000, 100)
        geom          (geo "Earth" earth)
        mat           (material "Common/MatDefs/Misc/Unshaded.j3md")]
    (set* mat :color "Color" ColorRGBA/Blue)
    (set* geom :material mat)
    (add-to-root geom)
    (-> (light :directional)
        (set* :direction (vec3 -0.1 -0.7 -1.0))
        (add-light-to-root))))


(defsimpleapp app
  :init init
  :opts {:settings             {:title          "JME CLJ Earth"
                                :dialog-image   "images/jme-clj-earth-splash.png"}})


(defn -main
  "This should be pretty simple."
  [& args]
  (start app))


