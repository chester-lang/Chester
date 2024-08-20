#lang racket

(require (prefix-in html: scribble/html-render))

(define (search:render-mixin %)
  (class (html:render-mixin %)
    (init [search-box? #t])
    (super-new [search-box? search-box?])))

(require "index.scrbl")

(render (list doc)
        (list "index.html")
        #:render-mixin search:render-mixin)