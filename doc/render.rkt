#lang racket

; https://stackoverflow.com/questions/37234346/racket-scribble-how-to-add-a-searchbox

(require (prefix-in html: scribble/html-render))

(define (search:render-mixin %)
  (class (html:render-mixin %)
    (init [search-box? #t])
    (super-new [search-box? search-box?])))

(require "index.scrbl")
(require scribble/render)

(render (list doc)
        (list "index.html")
        #:render-mixin search:render-mixin)