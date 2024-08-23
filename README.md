# Chester Language - Trying to make a practical possibly unsound object-oriented functional dependently typed language with algebraic effects

It might look like?

```chester
trait 舞 <: Show;

@derive(Show)
record 超会議 <: 舞 {
  let year: Nat;
}

@derive(Show)
object InternetOverdose <: 舞;

module 超会議 {
  let バタフライ_グラフィティ: 舞 = 超会議(2017);
}
i: InternetOverdose.type = InternetOverdose;

sealed trait Expr[T: Type] {
  def eval: T;
}

record IVal <: Expr[Int] {
  let val: Int;
  override def eval = val;
}

record BVal <: Expr[Int] {
  let val: Bool;
  override def eval = val;
}

sealed trait Vect[n: Nat, T: Type] {
  def apply(index: Fin n): T = ?todo;
}

object Nil[T] <: Vect[0, T];
record Cons[n,T] <: Vect[n+1, T] {
  let head: T;
  let tail: Vect[n, T];
}
n: Nil.type[Int] = Nil;

proof1[T]: Nil[T] = Nil[T];
proof1[T] = ?hole;

// alternative syntax for GADT
data Vect2[n: Nat, T: Type] {
  case Nil[T] <: Vect[0, T];
  case Cons[n,T](head: T, tail: Vect[n, T]) <: Vect[n+1, T];
}

record MutableString {
  var name: String;
}

record MutableStringExplicit[a: Region] {
  var[a] name: String;
}

record Box[a] {
  var var: a;
}

record BoxExplicit[a][s: Region] {
  var[s] var: a;
}

// an effect for global variables?
module MutModule / Global {
  let a = Box(0);
}

// IO somehow gives an implicit Region?
entry: Unit / IO = {
  let a = MutableString("");
  a.name = "はっぱ - もうすぐ楽になるからね";
  println(a.name);
}

AnonymousRecordType: Type = {name: String};
record1: AnonymousRecordType = {
  name = "Ame-chan";
}

extension [T](xs: List[T]) {
  // & denotes a second class type that won't leave the scope. Nonlocal returns and additional algebraic effects can be used in a second class function. original idea: Brachthäuser and Schuster (2017)
  def map[U](f: & T -> U): List[U] = ?todo;
  // def map[U,e](f: T -> U / e): List[U] / e = ?todo;
}
```

## Reference

```
Brachthäuser, J. I., & Schuster, P. (2017). Effekt: Extensible algebraic effects in Scala (short paper). Proceedings of the 8th ACM SIGPLAN International Symposium on Scala (SCALA 2017), 67–72. https://doi.org/10.1145/3136000.3136007
```