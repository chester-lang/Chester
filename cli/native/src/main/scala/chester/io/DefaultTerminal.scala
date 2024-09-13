package chester.io

import cats.Id
import chester.repl.*
import com.eed3si9n.ifdef.*

@ifndef("readline")
implicit val DefaultTerminal: Terminal[Id] = LinenoiseTerminal // new SimpleTerminalFactory
@ifdef("readline")
implicit val DefaultTerminal: Terminal[Id] = ReadlineTerminal