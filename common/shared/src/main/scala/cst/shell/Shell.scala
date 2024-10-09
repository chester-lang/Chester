package cst.shell

case object Vararg

trait Shell {
  type Str

  implicit def fromString(s: String): Str

  implicit def fromInt(s: Int): IntNum

  implicit def fromBoolean(s: Boolean): Condition

  def template(context: StringContext, args: Str*): Str

  type Condition

  def compare(a: Str, b: Str): Condition

  def and(a: Condition, b: Condition): Condition

  def or(a: Condition, b: Condition): Condition

  def not(a: Condition): Condition

  type Command

  def exec(program: Str, args: (Str | Vararg.type)*): Command

  def captureStdout(command: Command): Str

  def captureCondition(
      command: Command,
      muteStdout: Boolean = false,
      muteStderr: Boolean = false
  ): Condition

  type Var[_]

  type IntNum

  final type Val = Str | IntNum

  sealed trait ValType[T <: Val] {}

  implicit object StrType extends ValType[Str]

  implicit object IntType extends ValType[IntNum]

  def varName[T <: Val](name: String)(using tag: ValType[T]): Var[T]

  type Statement

  def store[T <: Val](variable: Var[T], value: T)(using
      tag: ValType[T]
  ): Statement

  implicit def load[T <: Val](variable: Var[T])(using tag: ValType[T]): T

  implicit def commandToStatement(command: Command): Statement

  def multipleStatements(statements: Statement*): Statement

  def ifThenElse(
      cond: Condition,
      thenBranch: Statement,
      elseBranch: Option[Statement]
  ): Statement

}

class ShellMonad(using val shell: Shell) {
  sealed trait F[T] {
    def flatMap[U](f: T => F[U]): F[U]
  }

  implicit class FStr(value: shell.Str) extends F[shell.Str] {
    override def flatMap[U](f: shell.Str => F[U]): F[U] = f(value)
  }

  def FStrDeclare(variable: shell.Var[shell.Str]) = FStrStore(variable, "")

  case class FStrStore(variable: shell.Var[shell.Str], value: shell.Str)
      extends F[shell.Str] {
    override def flatMap[U](f: shell.Str => F[U]): F[U] =
      FStatementAppend(shell.store(variable, value), f(variable))
  }

  implicit class FInt(value: shell.IntNum) extends F[shell.IntNum] {
    override def flatMap[U](f: shell.IntNum => F[U]): F[U] = f(value)
  }

  def FIntDeclare(variable: shell.Var[shell.IntNum]) = FIntStore(variable, 0)

  case class FIntStore(variable: shell.Var[shell.IntNum], value: shell.IntNum)
      extends F[shell.IntNum] {
    override def flatMap[U](f: shell.IntNum => F[U]): F[U] =
      FStatementAppend(shell.store(variable, value), f(variable))
  }

  def FStore[T <: shell.Val](variable: shell.Var[T], value: T)(using
      tag: shell.ValType[T]
  ): F[T] = tag match {
    case _: shell.StrType.type => FStrStore(variable, value)
    case _: shell.IntType.type => FIntStore(variable, value)
  }

  implicit class FStatement(value: shell.Statement) extends F[Unit] {
    override def flatMap[U](f: Unit => F[U]): F[U] =
      FStatementAppend(value, f(()))
  }

  case class FStatementAppend[T](value: shell.Statement, result: F[T])
      extends F[T] {
    override def flatMap[U](f: T => F[U]): F[U] =
      FStatementAppend(value, result.flatMap(f))
  }

  extension [T <: shell.Val](x: T)(using tag: shell.ValType[T]) {
    def store(name: String): F[T] = FStore(shell.varName(name), x)
  }

}

extension [Cond](cond: Cond)(using shell: Shell, ev: Cond <:< shell.Condition)
  def &&(other: shell.Condition): shell.Condition = shell.and(cond, other)
  def ||(other: shell.Condition): shell.Condition = shell.or(cond, other)
  def unary_! : shell.Condition = shell.not(cond)

extension (sc: StringContext)(using shell: Shell)
  def sh(args: shell.Str*): shell.Str = shell.template(sc, args*)
