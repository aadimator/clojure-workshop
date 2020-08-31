(def memory-db (atom {}))
(defn read-db [] @memory-db)
(defn write-db [new-db] (reset! memory-db new-db))

(defn create-table
  [name]
  (write-db (assoc (read-db) name {:data [] :indexes {}})))

(defn drop-table
  [name]
  (write-db (dissoc (read-db) name)))

(defn select-*
  [table]
  (get-in (read-db) [table :data]))

(defn select-*-where
  [table, field, field-value]
  (let [db (read-db)
        index (get-in db [table :indexes field field-value])
        data (get-in db [table :data])]
    (get data index)))

(defn insert
  [table, record, id-key]
  (if-let [exists (select-*-where table id-key (record id-key))]
    (str "Record with " id-key " " (record id-key) " already exists. Aborting")
    (let [db (read-db)
          updated-db (update-in db [table :data] conj record)
          insert-idx (dec (count (get-in updated-db [table :data])))]
      (write-db (update-in updated-db [table, :indexes, :id] assoc (record id-key) insert-idx)))))