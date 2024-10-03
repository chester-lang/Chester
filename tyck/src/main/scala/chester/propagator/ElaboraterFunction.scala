package chester.propagator

import chester.syntax.concrete.*
import chester.syntax.core.*

trait ElaboraterFunction extends ProvideCtx with Elaborater {
  def elabFunction(expr: FunctionExpr, ty: CellId[Term], effects: CellId[Effects])(using localCtx: LocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): CellId[Term]
}

trait ProvideElaboraterFunction extends ElaboraterFunction {
  def elabArg(arg: Arg, effects: CellId[Effects])(using localCtx: MutableLocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): CellId[ArgTerm] = {
    require(arg.decorations.isEmpty, "decorations are not supported yet")
    val ty = elabTy(arg.ty)
    val default = arg.exprOrDefault.map(elab(_, ty, effects))
    val id = UniqId.generate[LocalV]
    val bind = newLocalv(arg.name.name, ty, id, arg.meta)
    val r = Reference.create(bind, id, arg)
    state.add(parameter.references, r)
    localCtx.update(_.add(ContextItem(arg.name.name, id, bind, ty, Some(r))))
    default match {
      case Some(defaultValue) =>
        Map3(bind, ty, defaultValue) { (bind, ty, defaultValue) =>
          ArgTerm(bind, ty, Some(defaultValue), arg.vararg)
        }
      case None =>
        Map2(bind, ty) { (bind, ty) =>
          ArgTerm(bind, ty, None, arg.vararg)
        }
    }
  }

  def elabTelescope(telescope: DefTelescope, effects: CellId[Effects])(using localCtx: MutableLocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): CellId[TelescopeTerm] = {
    // Process each argument in the telescope, updating the context
    val argTerms = telescope.args.map { arg =>
      elabArg(arg, effects)
    }

    // Combine the argument terms into a TelescopeTerm
    Map1(Traverse(argTerms)) { args =>
      TelescopeTerm(args.toVector, telescope.implicitly)
    }
  }

  def elabFunction(expr: FunctionExpr, ty: CellId[Term], effects: CellId[Effects])(using ctx: LocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): CellId[Term] = {
    // Start with a mutable local context based on the current context
    val mutableCtx = new MutableLocalCtx(ctx)

    // Elaborate each telescope and collect TelescopeTerms
    val telescopeTerms: Seq[CellId[TelescopeTerm]] = expr.telescope.map { telescope =>
      elabTelescope(telescope, effects)(using mutableCtx, parameter, ck, state)
    }

    // Process the return type, if provided
    val returnType: CellId[Term] = expr.resultTy match {
      case Some(rtExpr) =>
        elabTy(Some(rtExpr))(using mutableCtx.ctx, parameter, ck, state)
      case None =>
        newType(using ck, state)
    }

    // Process the body of the function using the updated context
    val bodyTerm: CellId[Term] = elab(expr.body, returnType, effects)(using mutableCtx.ctx, parameter, ck, state)

    // Build the function type by folding over the telescopes
    val functionType: CellId[Term] = {
      // Reverse the telescopes for right-associative type construction
      val reversedTelescopes = telescopeTerms.reverse

      // Fold over the telescopes to build the function type
      reversedTelescopes.foldLeft(returnType) { (accType, telescopeTerm) =>
        Map2(telescopeTerm, accType) { (tel, resultType) =>
          FunctionType(Vector(tel), resultType): Term
        }
      }
    }

    // Unify the expected type with the constructed function type
    unify(ty, functionType, expr)

    // Build the function term
    Map2(functionType, bodyTerm) { (funcTypeTerm, body) =>
      // Cast funcTypeTerm to FunctionType, since we know it must be one
      val funcType = funcTypeTerm.asInstanceOf[FunctionType]
      Function(funcType, body)
    }
  }
}