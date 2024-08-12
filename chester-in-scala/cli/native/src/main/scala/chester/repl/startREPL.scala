package chester.repl

import com.eed3si9n.ifdef.*

@ifndef("readline")
def startREPL(): Unit = REPLEngine(SimpleTerminal).start()
@ifdef("readline")
def startREPL(): Unit = REPLEngine(ReadlineTerminal).start()