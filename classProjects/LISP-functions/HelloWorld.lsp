;; Wah Saw Tamalar
(format t "Hello, World!")
(format t "~%List: ~a" (list '(a b c d e)))
;;Returns the last element of a list
(defun last-element (alist)
  (if (null (cdr alist));;If the list is nil
    (car alist);;Return the element
    (last-element(cdr alist));;Recursive call without the first element
  )
)
(format t "~%Getting last element: ~a" (last-element '(a b c d e)))
;;Helper function
(defun add-to-end (alist ele)
  (if (null alist);;If nil list
    (cons ele nil);;Add the element
    (if (null (cdr alist))
    (cons (car alist) (cons ele nil));;Add the element
    (cons (car alist) (add-to-end (cdr alist) ele));;Resursive call without losing elements
    )
  )
)
;;Reverses the order of items in a list
(defun reverse-list (alist)
  (if (null alist)
    '();;Return empty list if the list is nil
    (add-to-end (reverse-list (cdr alist)) (car alist));;Resursive call inside the helper function
  )
)
(format t "~%Reverse the list: ~a" (reverse-list '(a b c d e)))
;;Finds the greatest value in a list of numbers.
(defun max-num (alist)
  (if (null (cdr alist))
    (car alist)
    (let ((rest-max (max-num (cdr alist))));;Resursive call and store it in rest-max
      (if (> (car alist) rest-max);;Comparing
        (car alist)
        rest-max
      )
    )
  )
)
(format t "~%List: ~a" (list '(4 3 6 8 2 9)))
(format t "~%Greatest value: ~a" (max-num '(4 3 6 8 2 9)))
;;Removes all the zeros from a list of numbers, returning the new list.
(defun remove-zero (alist)
  (cond
    ((null alist) '())
    ((zerop (car alist)) (remove-zero(cdr alist)));;Checking if the element is zero
    (t (cons (car alist) (remove-zero(cdr alist))));;Add the element
  )
)
(format t "~%List: ~a" (list '(1 0 2 4 0 5)))  
(format t "~%No-Zero: ~a" (remove-zero '(1 0 2 4 0 5)))
;;Finding the median
(defun finding-median(alist)
  (let ( (len-alist (length alist)) (sorted (sort alist #'>)))
    (if (= 1 len-alist)
      (first alist)
      (if (oddp len-alist)
        (nth (/ (- len-alist 1) 2) sorted)
        (nth (/ len-alist 2) sorted)
      )
    )
  )
)
(format t "~%Getting last element: ~a" (finding-median '(1 2 5 6)))
;; Sum all the values in the list
(defun sum-list(alist)
  (if (null alist);; If the list is nil
    0;; Returns 0
    (+ (car alist) (sum-list (cdr alist)));; Add the first value and the rest of the vaule in the list
  )
)
(format t "~a" (sum-list '(1 2 3)))


