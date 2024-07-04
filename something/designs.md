# Designs

## Algebraic Effect

Do we want to state effects everywhere?
Do we want a method to state effects less?

Could you lift something automatically?

Like `(a=>b)=>b` To `(a=>e b)=>e b`

Could you allow automatically adding effects to functions in data structures? That means that all a=>b in the context of IO becomes a=>IO b