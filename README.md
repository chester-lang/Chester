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

// InternetOverdose is overloaded with `Type` and `InternetOverdose`. For design: using `.instance` and `.type` for distinguishing doesn't look good? 
it: Type = InternetOverdose;
i: InternetOverdose = InternetOverdose;

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

```chester
tuple0: TupleN[Integer];
tuple0 = (1, 1, 1);
// Tuple: [t: Type *] -> Type
tuple1: Tuple[Integer, Integer, Integer] = (1,1,1);
tuple0ToLisst: List[Integer] = tuple0.toList;

list0: List[Integer] = [1,1,1];
list0ToTuple: TupleN[Integer] = list0.toTuple;

extension [T](list: List[T]) {
  map1[e: Effect, U](f: T -> e U): e List[U] = ?todo;
}
```