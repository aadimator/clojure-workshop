(defn encode-letter
  [s x]
  (let [code (Math/pow (+ x (int (first (char-array s)))) 2)]
    (str "#" (int code))))

(defn encode
  [s]
  (let [number-of-words (count (clojure.string/split s #" "))]
    (clojure.string/replace s #"\w"
                            (fn [s]
                              (encode-letter s number-of-words)))))

(encode "Super secret")

(defn decode-letter
  [s n]
  (let [number (Integer/parseInt (subs s 1))
        letter (char (- (Math/sqrt number) n))]
    (str letter)))

(defn decode
  [s]
  (let
   [number-of-words
    (count (clojure.string/split s #" "))]
    (clojure.string/replace s #"\#\d+"
                            (fn [s]
                              (decode-letter s number-of-words)))))

(decode (encode "aadam"))