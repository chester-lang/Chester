# Chester Language

It might look like?

```chester
module 😿😿;

data #abstract 舞 extends Show;

@derive(Show)
data 超会議 extends 舞 {
  field year: Nat;
}

@derive(Show)
data InternetOverdose extends 舞;

data #sealed #abstract Expr[T: Type]: Type {
  eval: T;
}

data IVal extends Expr[Integer] {
  field val: Integer;
  #override eval = val;
}

// IVal is overloaded with `Type` and `Object { val = Integer } -> IVal`
// Object here is a syntax/macro and doesn't have a type if we consider it as a function.
ival0: IVal = IVal { val = 0 };

data BVal extends Expr[Boolean] {
  field val: Boolean;
  #override eval = val;
}

data #sealed #abstract Vector[n: Nat, T: Type];

data Nil extends Vector[0, T] {
}

data Cons extends Vector[succ(n), T] {
  field head: T;
  field tail: Vector[n, T];
}

proof1: Nil = Nil;
proof1 = ?hole;
```