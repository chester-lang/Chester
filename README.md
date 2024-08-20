# Chester Language - Trying to make a practical possibly unsound object-oriented functional dependently typed language with algebraic effects

It might look like?

```chester
trait 舞 <: Show;

@derive(Show)
data 超会議 <: 舞 {
  let year: Nat;
}

@derive(Show)
data InternetOverdose <: 舞;

a: 舞 = new 超会議(2017);
i: InternetOverdose = new InternetOverdose;

trait #sealed Expr[T: Type] {
  def eval: T;
}

data IVal <: Expr[Int] {
  let val: Int;
  def #override eval = val;
}

data BVal <: Expr[Int] {
  let val: Bool;
  def #override eval = val;
}

trait #sealed Vect[n: Nat, T: Type] {
  def apply(index: Fin n): T = ?todo;
}

data Nil[T] <: Vect[0, T];
data Cons[n,T] <: Vect[n+1, T] {
  let head: T;
  let tail: Vect[n, T];
}

proof1[T]: Nil[T] = Nil[T];
proof1[T] = ?hole;
```