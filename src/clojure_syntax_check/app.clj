(ns clojure-syntax-check.app
  (require
    [clojure.java.io :as io]
    [clojure.string :as str]
    )
  )

(defn sanitize-message[message]
  (str/replace message #"^java\.lang\.RuntimeException: " "")
  )

(defn parse-error-message[message line-number]
  (let [
        matcher (re-matcher #"(.+), starting at line (\d+)$" message)
        match (re-find matcher)
        ]
      (if match
        (let [groups (re-groups matcher)]
          [(sanitize-message (get groups 1)) (Integer/parseInt (get groups 2))]
          )
        [(sanitize-message message) line-number]
        )
    )
  )

(defn validate[stream]
  "Validate that the stream contains valid Clojure syntax"
  (binding [*read-eval* false]
    (let [reader (clojure.lang.LineNumberingPushbackReader. (io/reader stream))]
      (try
        (loop [reader-result nil]
          (if (= reader-result :clojure-syntax-check-end-of-file-reached)
            nil
            (recur (read reader false :clojure-syntax-check-end-of-file-reached))
            )
          )
        (catch java.lang.RuntimeException ex
          (println (parse-error-message (.getMessage ex) (.getLineNumber reader)))
          )
        )
      )
    )
  )
