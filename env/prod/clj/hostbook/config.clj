(ns hostbook.config
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[hostbook started successfully]=-"))
   :middleware identity})
