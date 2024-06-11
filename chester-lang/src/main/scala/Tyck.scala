package chester.lang


class TyckMutableState{

}

class Tyck(state: TyckMutableState) {

  def infer(ast: AST): The = ???
  def check(ast: AST, itsType: Expr): The = ???
}
