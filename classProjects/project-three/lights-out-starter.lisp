;; lights-out-starter.lisp
;; Wah Saw Tamalar
;; The board is represented by a list 9 long
;; which is a 3x3 board.
;;
(defun print-board (board)
  (if (= 0 (mod (length board) 3))
    (format t "~% ~a" (car board))
    (format t " ~a" (car board))
  )
  (if (null (rest board))
      nil
      (print-board (rest board)))
)

;; toggles the element at spot which
;; which is zero-based spot in the list
(defun toggle-one (board which)
  (if (zerop which)
      ;; look at contents of current spot
      (if (zerop (car board))
          (cons 1 (rest board))
          (cons 0 (rest board))
      )
      ;; Not the current, so toggle further down the list
      (cons (car board) (toggle-one (cdr board) (- which 1)))
  )
)

;; Check if the board is solved, all the lights are off.
;; Returns a boolean, true or false.
(defun all-lights-out-p(board)
  (cond
    ((null board) t);; If the board is null, return True
    ((zerop (car board)) (all-lights-out-p(cdr board)));; Rescursive call checking if the all the elements are zeros
    (t nil);; If not all zeros, return Nil/False
  )
)

;; Checking if the board/list is all 0s and 1s.
;; Returns a boolean, true or false.
(defun valid-board-p-helper(board)
  (if (null board);; If the list is nil
    t ;; Returns true
    (and 
      (or (equal (car board) 0) (equal (car board) 1));; Checking if its 0 or 1
      (valid-board-p-helper (cdr board));; Rescursive call to check each element
    )
  )
)

;; Check if a board is valid.
;; Check if the board is the right size, 9 length.
;; Returns a boolean, true or false.
(defun valid-board-p(board)
  (and
    (= (length board) 9);; Checking if the board/list is length 9
    (valid-board-p-helper board);; Calls helper function
  )
)

;; Prompts user for some input.
;; Returns the typed number.
;; Enforces the 1 to 9 value of typed text.
(defun get-input ()
  (format t "~%Enter a switch to toggle (1-9): ")
  (let ( (usernum (read)))
    (if (or (< usernum 1) (> usernum 9))
       (get-input)
       usernum
    )
  )
)

;; Create a 9 length board/list of 0s and 1s randomly
;; Returns a list of 0s and 1s.
(defun create-board()
  (list (random 2) (random 2) (random 2) 
        (random 2) (random 2) (random 2) 
        (random 2) (random 2) (random 2))
)

;; Toggle Adjacent lights
;; Lets inside lets
;; Returns a board/list
;; Range 0-8
(defun switch (board choice)
  (let ((b1 (toggle-one board choice)));; Toggle user choice
    ;; Top Cell
    (let ((b2 (if (>= (- choice 3) 0);; Check if above cell needs toggle
                  (toggle-one b1 (- choice 3))
                  b1)))
      ;; Bottom Cell
      (let ((b3 (if (< (+ choice 3) (length board));; Check if below cell needs toggle
                    (toggle-one b2 (+ choice 3))
                    b2)))
        ;; Left Cell
        (let ((b4 (if (/= (mod choice 3) 0);; Check if left cell needs toggle
                      (toggle-one b3 (- choice 1))
                      b3)))
          ;; Right Cell
          (let ((b5 (if (/= (mod choice 3) 2);; Check if right cell needs toggle
                        (toggle-one b4 (+ choice 1))
                        b4)))
            b5)))))
)

;; Starts game
(defun start-game()
  (let ( (board (create-board)));; Create a board/list
    (game-loop board 0);; Calls helper
  )
)

;; Game loop, start-game's helper function
;; Looping till it is solved. 
;; Returns win statements and moves when solved.
(defun game-loop(board moves)
  (format t "~%-------")
  (print-board board);; Print board
  (if (all-lights-out-p board);; Check if it is solved
    (format t "~%You've won!~%Moves: ~a" moves);; Winner statement and moves
    (if (>= moves 25);; Moves limits
      (format t "~%Unsolved! You've Failed!")
      (let ( (choice (get-input)));; Get user input
      (game-loop (switch board (- choice 1)) (+ moves 1));; Toggle user choice and increment moves
      )
    )
  )
)

;; Random AI
;; AI will pick random moves
;; AI will have 25 moves to solve it
;; Generate random moves
;; Returns number of moves or if Unsolved, return nil
(defun random-ai-loop(board moves)
  (if (all-lights-out-p board);; Checking if the board is solved
    (let ((result (format t "~%Random AI solved! Moves: ~a" moves))) moves);; Solved statement, store result for averages calculation, and return moves
    (if (>= moves 50);; Moves limits
      (let ((result (format t "~%Random AI Failed!"))) nil);; Failed statement, return nil
      (let ( (choice (random 9)));; Generate a random 1-9 for moves
        (random-ai-loop (switch board choice) (+ moves 1));; Toggle AI choice and increment moves
      )
    )
  )
)

;; Create my own ai, using Chase the Light method
;; If the top two rows is not solved(all zeros), solve for the top two row using find-first-one
;; When the top two rows condition is met, there are 7 possibilities.
;; Lot of hard-coded.
;; Returns number of moves or if Unsolved, return nil
(defun my-ai (board moves)
  (if (all-lights-out-p board);; Checking if the board is solved
    (let ((result (format t "~%My AI solved! Moves: ~a" moves))) moves);; Solved statement, store result for averages calculation, and return moves
    (if (>= moves 50);; Moves limits
      (let ((result (format t "~%My AI Failed!"))) nil);; Failed statement, retunr nil
      (if (top-two-row-p board);; Checking if the top two rows is solved
        (cond
          ( (equal board '(0 0 0 0 0 0 0 1 1)) (my-ai (apply-moves board '(0 3 4 6 8)) (+ moves 5)));; Make these moves to solve the board and add the moves
          ( (equal board '(0 0 0 0 0 0 1 1 0)) (my-ai (apply-moves board '(2 4 5 6 8)) (+ moves 5)))
          ( (equal board '(0 0 0 0 0 0 0 1 0)) (my-ai (apply-moves board '(0 1 2 4)) (+ moves 4)))
          ( (equal board '(0 0 0 0 0 0 1 1 1)) (my-ai (apply-moves board '(1 3 4 5)) (+ moves 4)))
          ( (equal board '(0 0 0 0 0 0 0 0 1)) (my-ai (apply-moves board '(1 2 3 6 8)) (+ moves 5)))
          ( (equal board '(0 0 0 0 0 0 1 0 0)) (my-ai (apply-moves board '(0 1 5 6 8)) (+ moves 5)))
          ( (equal board '(0 0 0 0 0 0 1 0 1)) (my-ai (apply-moves board '(0 2 3 5)) (+ moves 4)))
        )
        (my-ai (switch board (+ (find-first-one board 0) 3)) (+ moves 1));; Solve for the top two rows, add 3 to switch the bottom node to turn the top rows off/zero
      )
    )
  )
)

;; Checking if the top two rows is solved, my-ai's helper
;; Returns true or false
(defun top-two-row-p (board)
  (and
    (zerop (nth 0 board))
    (zerop (nth 1 board))
    (zerop (nth 2 board))
    (zerop (nth 3 board))
    (zerop (nth 4 board))
    (zerop (nth 5 board))
  )
)

;; Find the index of the first one, my-ai's helper
;; Returns the index of the first one
(defun find-first-one(board index)
  (cond 
    ( (null board) nil)
    ( (/= (car board) 0) index);; If the element is 0, return the index
    (t (find-first-one (cdr board) (+ index 1)));; Else increment the index
  )
)

;; Apply list of moves, my-ai's helper
;; Returns a new board
(defun apply-moves(board move-list)
  (if (null move-list)
    board
    ( let( (new-board (switch board (car move-list))))
      (apply-moves new-board (cdr move-list))
    )
  )
)

;; AIs runner
(defun run-ai(runs random-ai-moves my-ai-moves)
  (if (zerop runs)
    (average-moves-ais random-ai-moves my-ai-moves)
    (let* ((board (create-board))
          (random-result (random-ai-loop board 0))
          (my-ai-result (my-ai board 0)))
      (run-ai (- runs 1)
        (if random-result
          (cons random-result random-ai-moves)
          random-ai-moves
        )
        (if my-ai-result
          (cons my-ai-result my-ai-moves)
          my-ai-moves
        )
      )
    )
  )
)

;; Options for user or AIs to play the game
(defun game-opt()
  (format t "~%Enter 1 to play Game or 2 to watch AIs battle: ")
  (format t "~%Game Start!")
  (let ( (useropt (read)))
    (if (equal useropt 1)
      (start-game)
      (run-ai 50 '() '());; Runs AIs for 50 games and calculate averages
    )
  )
)

;; Compare random AI and my AI
;; Returns the average number of moves for random AI and my AI
(defun average-moves-ais(random-ai-moves my-ai-moves)
  (let (( random-average (/ (sum-list random-ai-moves) (length random-ai-moves)))
       ( my-ai-average (/ (sum-list my-ai-moves) (length my-ai-moves))))
    (format t "~%Random Average: ~a" (float random-average))
    (format t "~%My AI Average: ~a" (float my-ai-average))
  )
)

;; Sum of the list, average-moves-ais's helper
(defun sum-list(alist)
  (if (null alist)
    0
    (+ (car alist) (sum-list (cdr alist)))
  )
)


;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;; Unit Tests ;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;
(defun ut-toggle-one ()
  (and
     (equal '(0 1 0 1) (toggle-one '(0 1 0 0) 3))
     (equal '(0 0 0 1 1 1 0 0 0) (toggle-one '(0 0 1 1 1 1 0 0 0) 2))
     (not (equal '(0 0 1) (toggle-one '(0 0 1) 2)))
  )
)

;; A unit test for all-lights-out-p
(defun ut-all-lights-out-p()
  (and
    (equal t (all-lights-out-p '(0 0 0 0 0 0 0 0 0)))
    (not (equal t (all-lights-out-p '(0 0 1 0 0 0 0 0 1))))
  )
)

;; A unit test for valid-board-p
(defun ut-valid-board-p()
  (and 
    (equal t (valid-board-p '(0 1 0 1 0 1 0 1 0)))
    (equal nil (valid-board-p '(0 0 0 0 1)))
    (equal nil (valid-board-p '(000000000)))
    (equal nil (valid-board-p '(1 2 3 4 5 6 7 8 9)))
    (equal nil (valid-board-p '(a b c 1 2 3 4 5 6)))
    (equal nil (valid-board-p '(0 0 0 0 010 0 0 0 10)))
  )
)

;; A unit test for switch
(defun ut-switch()
  (and
    (equal '(1 0 1 0 1 0 0 0 0) (switch '(0 1 0 0 0 0 0 0 0) 1));; Toggle top left corner
    (equal '(1 0 0 1 0 0 0 0 0) (switch '(0 1 0 0 0 0 0 0 0) 0));; Toggle middle top
    (equal '(0 1 0 0 0 1 0 1 1) (switch '(0 1 0 0 0 0 0 0 0) 8));; Toggle bottom right
  )
)

;; A unit test for get-input, not sure what to test
(defun ut-get-input()
  (and
    (equal 2 (get-input))
    (equal 4 (get-input))
  )
)

;; A unit test for one move
(defun ut-play-one-move()
  (print-board '(0 0 0 0 1 0 0 0 0))
  (print-board (switch '(0 0 0 0 1 0 0 0 0) (get-input)))
)

;; A unit test for top-two-row-p
(defun ut-top-two-row-p ()
  (and
    (equal t (top-two-row-p '(0 0 0 0 0 0 1 1 1)))
    (equal nil (top-two-row-p '(0 0 0 0 1 0 1 1 0)))
  )
)

;; A unit test for find-first-one
(defun ut-find-first-one()
  (and 
    (equal 0 (find-first-one '(1 0 0 0 0 0 1 0 1) 0))
    (equal 4 (find-first-one '(0 0 0 0 1 0 0 1 1) 0))
  )
)

;; A unit test for apply-moves
(defun ut-apply-moves()
  (and
    (equal '(0 0 0 0 0 0 0 0 0) (apply-moves '(0 0 0 0 0 0 0 1 1) '(0 3 4 6 8)))
    (equal '(0 0 0 0 0 0 0 0 0) (apply-moves '(0 0 0 0 0 0 1 1 0) '(2 4 5 6 8)))
  )
)

(princ "Units Test")
(format t "~%Testing toggle-one: ~a" (ut-toggle-one))
(format t "~%Testing all-lights-out-p: ~a" (ut-all-lights-out-p))
(format t "~%Testing valid-board-p: ~a" (ut-valid-board-p))
(format t "~%Testing switch: ~a" (ut-switch))
(format t "~%Inputs 2 and 4 for get-input unit test")
(format t "~%Testing get-input: ~a" (ut-get-input))
(format t "~%Testing one move:")
(format t "~%~a" (ut-play-one-move))
(format t "~%Testing top-two-row-p: ~a" (ut-top-two-row-p))
(format t "~%Testing find-first-one: ~a" (ut-find-first-one))
(format t "~%Testing apply-moves: ~a" (ut-apply-moves))
(game-opt)