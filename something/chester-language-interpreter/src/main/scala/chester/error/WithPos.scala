package chester.error

trait WithPos {
  private var sourceLocationVar: Option[SourcePos] = None

  def sourceLocation: Option[SourcePos] = sourceLocationVar

  def withSourceLocation(location: SourcePos): this.type = {
    assert(sourceLocationVar.isEmpty, "Source location already set")
    sourceLocationVar = Some(location)
    this
  }
}
