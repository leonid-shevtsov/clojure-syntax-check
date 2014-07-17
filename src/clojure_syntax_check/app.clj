(ns clojure-syntax-check.app
  (require
    [clojure.java.io :as io]
    [clojure.string :as str]
    )
  )

(defn skip-whitespace[string]
  "Skip all clojure whitespace and comments at the beginning of a string"
  (str/replace string #"(?sx)
                          ^\s*
                          (\s*
                           (;[^\n]*)?
                           \n
                          )*" "")
  )

(defn find-error-position[full-string broken-string-with-whitespace]
  (let [broken-string (skip-whitespace broken-string-with-whitespace)
        working-string (.substring full-string 0 (- (.length full-string) (.length broken-string)))
        newlines-in-working-string (str/replace working-string #"[^\n]" "")
        line-number (inc (count newlines-in-working-string))
        last-line (re-find #"(?s)\n[^\n]*$" working-string)
        char-number (inc (count last-line))
        ]
      [line-number char-number]
    )
  )

(defn validate[stream]
  "Validate that the stream contains valid Clojure syntax"
  (binding [*read-eval* false]
    (let [input-string (slurp stream)
          string-reader (java.io.StringReader. input-string)
          ; line-counter (java.io.LineNumberReader. string-reader)
          reader (clojure.lang.LineNumberingPushbackReader. string-reader)
          ]
      (try
        (loop [reader-result nil]
          (.mark string-reader 0)
          (if (= reader-result :clojure-syntax-check-end-of-file-reached)
            nil
            (recur (read reader false :clojure-syntax-check-end-of-file-reached))
            )
          )
        (catch java.lang.RuntimeException ex
          (.reset string-reader)
          (println (.getMessage ex))
          (println (find-error-position input-string (slurp string-reader)))
          )
        )
      )
    )
  )
