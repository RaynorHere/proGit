(define (copy source target replacement)
    (cond ((equal? source target) replacement)
          ((not (pair? source)) source)
          (else (cons (copy (car source) target replacement)
                      (copy (cdr source) target replacement)))
    )
)

(define replace
    (lambda (source search-for replace-with)
      (copy source search-for replace-with)
    )
)