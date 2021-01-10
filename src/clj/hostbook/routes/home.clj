(ns hostbook.routes.home
  (:require [hostbook.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [hostbook.db.core :as db]
            [ring.util.response :refer [response status]]))

(defn home-page []
  (layout/render "home.html"))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/messages" [] (response (db/get-messages)))
  (GET "/about" [] (about-page)))

