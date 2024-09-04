package chester.doc

import Doc.*
import munit.FunSuite

class DocRenderingTests extends FunSuite {

  test("Render single text") {
    val doc = text("Hello, World!")
    val rendered = render(doc, 80)(StringRenderer)
    assertEquals(rendered, "Hello, World!")
  }

  test("Render concatenated text") {
    val doc = text("Hello") <+> text("World")
    val rendered = render(doc, 80)(StringRenderer)
    assertEquals(rendered, "Hello World")
  }

  test("Render new line text") {
    val doc = text("Hello") </> text("World")
    val rendered = render(doc, 80)(StringRenderer)
    assertEquals(rendered, "Hello World")
  }

  test("Render grouped text") {
    val doc = group(text("Grouped Text"))
    val rendered = render(doc, 80)(StringRenderer)
    assertEquals(rendered, "Grouped Text")
  }

  test("Render empty text") {
    val doc = text("")
    val rendered = render(doc, 80)(StringRenderer)
    assertEquals(rendered, "")
  }

  test("Render indented text with spaces") {
    val doc = indented(text("Indented Text"), Indent.Spaces(4))
    val rendered = render(doc, 80)(StringRenderer)
    assertEquals(rendered, "    Indented Text")
  }

  test("Render indented text with tabs") {
    val doc = indented(text("Indented Text"), Indent.Tab)
    val rendered = render(doc, 80)(StringRenderer)
    assertEquals(rendered, "\tIndented Text")
  }

  test("Render mixed document") {
    val doc = text("Hello") <+> text("World") </> indented(text("Indented Text"), Indent.Spaces(2))
    val rendered = render(doc, 999)(StringRenderer)
    assertEquals(rendered, "Hello World Indented Text")
  }
  test("Render mixed document") {
    val doc = text("Hello") <+> text("World") </> indented(text("Indented Text"), Indent.Spaces(2))
    val rendered = render(doc, 2, useCRLF = false)(StringRenderer)
    assertEquals(rendered, "Hello World\n  Indented Text")
  }
}
