(ns hostbook.routes.home
  (:require [hostbook.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [hostbook.db.core :as db]
            [bouncer.core :as b]
            [bouncer.validators :as v]
            [ring.util.response :refer [response status]]))

(defn home-page []
  (layout/render
    "home.html"))

(defn validate-message [params]
  (first
   (b/validate
    params
    :name v/required
    :message [v/required [v/min-count 10]])))

(defn save-message! [{:keys [params]}]
  (if-let [errors (validate-message params)]
    (response/bad-request {:errors errors})
    (try
      (db/save-message!
       (assoc params :timestamp (java.util.Date.)))
      (response/ok {:status :ok})
      (catch Exception e
        (response/internal-server-error
         {:erorrs {:server-error ["Failed to save message!"]}})))))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/messages" [] (response (db/get-messages)))
  (POST "/add-message" req (save-message! req))
  (GET "/about" [] (about-page)))

