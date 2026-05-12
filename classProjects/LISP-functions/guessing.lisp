;; guessing.lisp
;;
;; Implement a guess-the-number game, guessing from 1 to 100.
;; The computer will pick a number and the user tries to guess it.
;; The computer tells the user if their guess is too high or too low.
;;
;; Author: Erik Steinmetz
;;


;; Prompts user for some input.
;; Returns the typed number.
;; Enforces the 1 to 100 value of typed text.
(defun get-input (prompt)
  (princ  prompt)
  (let ( (usernum (read)))
    (if (or (< usernum 1) (> usernum 100))
       (get-input "Number must be 1-100. Enter again: ")
       usernum
    )
  )
)

;; Take one guess at a target number.
;; When the guess matches, the game exits
;; Otherwise makes a recursive call to guess again.
(defun one-guess (target message count)
  (let ( (guess (get-input message)) )
    (if (= guess target)
        (format T "You matched after ~D guesses." count)
        (if (< guess target)
            (one-guess target 
                       "Too low, guess again: " 
                       (+ 1 count))   ;; too low
            (one-guess target 
                       "Too high, guess again: " 
                       (+ 1 count)) ;; too high
        ) ; end if
    ) ; end if
  )   ; end let
)

;; Starts the number guessing game.
(defun start-game ()
  (format t "Guess a number from 1 to 100.~%")
  (one-guess                   ;; calls the turn function with:
    (+ 1 (random 100))         ;; the number to be guessed
    "Please enter a number: "  ;; the prompt
    1                          ;; the count
   )
)
