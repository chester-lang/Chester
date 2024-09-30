package chester.error

import chester.utils.doc.{Doc, PrettierOptions, ToDoc}
import upickle.default.*

object Problem {
  enum Stage  derives ReadWriter {
    case TYCK, PARSE, OTHER
  }

  enum Severity  derives ReadWriter {
    case ERROR, GOAL, WARN, INFO
  }
}

trait WithServerity extends Any {
  def level: Problem.Severity

  final def isError: Boolean = level == Problem.Severity.ERROR
}

trait Problem extends ToDoc with WithServerity {
  def stage: Problem.Stage
}

private case class ProblemSer(stage: Problem.Stage, level: Problem.Severity, message: Doc) extends Problem  derives ReadWriter  {
  override def toDoc(using options: PrettierOptions): Doc = message
}

private object ProblemSer {
  def from(problem: Problem): ProblemSer = ProblemSer(problem.stage, problem.level, problem.toDoc(using PrettierOptions.Default))
}

object ProblemUpickle {
  implicit val problemRW: ReadWriter[Problem] = readwriter[ProblemSer].bimap(ProblemSer.from, x=>x)
}