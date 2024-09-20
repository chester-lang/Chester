'use client';

import { useEffect, useRef } from "react";
import Image from "next/image";
import { XTerm } from "@pablo-lion/xterm-react";
import { startRepl, startReplPty, startReplReadline } from "../generated/main.mjs";
import "../types/main.d.ts";
import { Terminal } from '@xterm/xterm';
import { Readline } from "xterm-readline";

export default function Home() {
  /* eslint-disable @typescript-eslint/no-explicit-any */
  const xtermRef = useRef<any>(null);

  useEffect(() => {
    // https://stackoverflow.com/questions/66096260/why-am-i-getting-referenceerror-self-is-not-defined-when-i-import-a-client-side/66100185#66100185
    const initTerminal = async () => {
      if (xtermRef.current) {
        const terminal = xtermRef.current.terminal as Terminal;
        const { WebLinksAddon } = await import('@xterm/addon-web-links');
        terminal.loadAddon(new WebLinksAddon());
        //const { FitAddon } = await import('@xterm/addon-fit');
        //const fitAddon = new FitAddon();
        //terminal.loadAddon(fitAddon);
        //fitAddon.fit();
        if(false){
          startRepl(terminal);
        }else if(false){
          // @ts-expect-error xterm-pty types are not recognized
          const {openpty} = await import('xterm-pty');
          const { master, slave } = openpty();
          terminal.loadAddon(master);
          startReplPty(slave);
        }else{
          const rl = new Readline();
          terminal.loadAddon(rl);
          startReplReadline(rl);
        }
        terminal.focus();
      }
    }
    initTerminal();
  }, []);

  return (
    <div className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
      <main className="flex flex-col gap-8 row-start-2 items-center sm:items-start">
        <Image
          className="dark:invert"
          src="/chester-logo.svg"
          alt="Chester logo"
          width={180}
          height={38}
          priority
        />
        <h1 className="text-2xl font-bold">Chester: A Programming Language</h1>
        <div className="w-full h-96 bg-black rounded">
          <XTerm ref={xtermRef} />
        </div>
      </main>
      <footer className="row-start-3 flex gap-6 flex-wrap items-center justify-center">
        <a
          className="flex items-center gap-2 hover:underline hover:underline-offset-4"
          href="https://nextjs.org/learn?utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
          target="_blank"
          rel="noopener noreferrer"
        >
          <Image
            aria-hidden
            src="https://nextjs.org/icons/file.svg"
            alt="File icon"
            width={16}
            height={16}
          />
          Learn
        </a>
        <a
          className="flex items-center gap-2 hover:underline hover:underline-offset-4"
          href="https://vercel.com/templates?framework=next.js&utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
          target="_blank"
          rel="noopener noreferrer"
        >
          <Image
            aria-hidden
            src="https://nextjs.org/icons/window.svg"
            alt="Window icon"
            width={16}
            height={16}
          />
          Examples
        </a>
        <a
          className="flex items-center gap-2 hover:underline hover:underline-offset-4"
          href="https://nextjs.org?utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
          target="_blank"
          rel="noopener noreferrer"
        >
          <Image
            aria-hidden
            src="https://nextjs.org/icons/globe.svg"
            alt="Globe icon"
            width={16}
            height={16}
          />
          Go to nextjs.org →
        </a>
      </footer>
    </div>
  );
}
