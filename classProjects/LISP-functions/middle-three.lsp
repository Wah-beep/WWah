;;Wah Saw Tamalar
;;Return the middle value out of three parameters
(format t "Hello, World!")
;;Using if statements
(defun middle-3 (a b c)
  (if (and (>= a b) (<= a c))
    a
    (if (and (<= a b) (>= a c))
      a 
      (if (and (>= b a) (<= b c))
        b
        (if (and (<= b a) (>= b c))
          b
          c
        )
      )
    )
  )
)
;;Using cond
(defun middle-3-cond(a b c)
  (cond
    ((and (>= a b) (<= a c)) a)
    ((and (<= a b) (>= a c)) a)
    ((and (>= b a) (<= b c)) b)
    ((and (<= b a) (>= b c)) b)
    (t c)
  )
)
(format t "~a" (middle-3-cond 16 7 48))