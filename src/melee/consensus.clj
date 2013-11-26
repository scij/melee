(ns melee.consensus)

(defprotocol Consensus
  (vote [_ ballot])
  (append [_ entry]))

;;; Raft consensus algorithm
;;; http://raftconsensus.github.io/

(defrecord Ballot [^Number term candidate-id ^Number last-log-index ^Number last-log-term])

(defrecord Entry [^Number term leader-id ^Number prev-log-index ^Number prev-log-term entries ^Number leader-commit])

(defrecord State [id ^Number current-term voted-for log ^Number commit-index ^Number last-applied]
  Consensus
  (vote [_ ballot] {:term current-term :vote-granted (> (:term ballot) current-term)})
  (append [_ entry]))

(defrecord Leader [^State state next-index match-index])

(defn ballot [^Number term candidate-id ^Number last-log-index ^Number last-log-term]
  "Ballot for leader election."
  (Ballot. term candidate-id last-log-index last-log-term))

(defn entry [^Number term leader-id ^Number prev-log-index ^Number prev-log-term entries ^Number leader-commit]
  "Entry for log."
  (Entry. term leader-id prev-log-index prev-log-term entries leader-commit))

(defn state [id ^Number current-term voted-for log ^Number commit-index ^Number last-applied]
  "State for node."
  (State. id current-term voted-for log commit-index last-applied))

(defn leader [^State state next-index match-index]
  "Leader from state."
  (Leader. state next-index match-index))