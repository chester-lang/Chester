# Chester Language

It might look like?

```chester
module 😿😿;

me: String = "インターネット・エンジェル";
world = {
  execute = me: String -> me;
};

world.execute(me); // it type checks and does nothing? What's the point of having expressions at top level when they can't have effects? Checking something type checks? No we might allow module that init with specific effects.

data #abstract 舞 <: Show;

@derive(Show)
data 超会議 <: 舞 {
  let year: Nat;
}

@derive(Show)
data InternetOverdose <: 舞;

it: Type = InternetOverdose;
i: InternetOverdose = new InternetOverdose;
i2: InternetOverdose = InternetOverdose.new; // .new is defined by default if no user definition // .new it is a function with no telescope here
ia: Any = new InternetOverdose;

trait #sealed Expr[T: Type]: Type {
  eval: T;
}

data IVal <: Expr[Integer] {
  let val: Integer;
  #override eval = val;
}

ival0: IVal = new IVal { val = 0 };
ival02: IVal = IVal.new(val = 0);

data BVal <: Expr[Boolean] {
  field val: Boolean;
  #override eval = val;
}

data #sealed #abstract Vector[n: Nat, T: Type];

data Nil <: Vector[0, T] {
}

data Cons <: Vector[succ(n), T] {
  let head: T;
  let tail: Vector[n, T];
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

```chester
pragma #UnsizedType;

Expr = Integer | String | List[Expr];
```

```chester
pragma #effect(IO);

functionCanEmitEffect(): Unit = println("はっぱ - もうすぐ楽になるからね");
```

## Features, namings and designs are copied from:

+ Scala
+ Haskell
+ Swift
+ Aya
+ maybe Rust
+ Koka
+ Racket
+ Nix
+ Javascript, Typescript
+ Idris
+ https://github.com/inkytonik/kiama