package com.example.example2

inline def myMethod(inline f: Int => Int): Int = f(1)
val test = myMethod(_ + 1) // expands to 1 + 1