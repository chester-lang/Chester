import { useEffect, useRef } from "react";
import { XTerm } from "@pablo-lion/xterm-react";
import { WebLinksAddon } from '@xterm/addon-web-links';
import { Terminal } from '@xterm/xterm';
import { startRepl } from "../../generated/main.mjs";

export default function Repl() {
    /* eslint-disable @typescript-eslint/no-explicit-any */
  const xtermRef = useRef<any>(null);

  useEffect(() => {
    if (xtermRef.current) {
      const terminal = xtermRef.current.terminal as Terminal;
      terminal.loadAddon(new WebLinksAddon());
      startRepl(terminal);
    }
  }, []);

  return (
    <div className="w-full h-96 bg-black rounded">
      <XTerm ref={xtermRef} />
    </div>
  );
}