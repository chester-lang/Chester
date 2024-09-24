'use client';

import { useEffect, useRef } from "react";
import { XTerm } from "@pablo-lion/xterm-react";
import { startRepl, startReplPty, startReplReadline } from "../../generated/main.js";
import "../../types/main.d.ts";
import { Terminal } from '@xterm/xterm';
import { Readline } from "xterm-readline";
import { useTranslations } from 'next-intl';

export default function Home() {
  const t = useTranslations('Home');
  const xtermRef = useRef<any>(null);

  useEffect(() => {
    // https://stackoverflow.com/questions/66096260/why-am-i-getting-referenceerror-self-is-not-defined-when-i-import-a-client-side/66100185#66100185
    const initTerminal = async () => {
      if (xtermRef.current) {
        const terminal = xtermRef.current.terminal as Terminal;
        const { WebLinksAddon } = await import('@xterm/addon-web-links');
        terminal.loadAddon(new WebLinksAddon());
        const { FitAddon } = await import('@xterm/addon-fit');
        const fitAddon = new FitAddon();
        terminal.loadAddon(fitAddon);
        fitAddon.fit();
        terminal.focus();
          const used: string = 'startReplReadline';
            if (used === 'startRepl'){
              while(true){
                try{
                  await startRepl(terminal);
                }catch(e){
                  console.log(e);
                }
              }
            }else if(used === 'startReplPty'){
              // @ts-expect-error xterm-pty types are not recognized
              const {openpty} = await import('xterm-pty');
              const { master, slave } = openpty();
              terminal.loadAddon(master);
              while(true){
                try{
                  await startReplPty(slave);
                }catch(e){
                  console.log(e);
                }
              }
            } else if (used === 'startReplReadline') {
              const rl = new Readline();
              terminal.loadAddon(rl);
              while(true){
                try{
                  await startReplReadline(rl);
                }catch(e: any){
                  console.log(e);
                  rl.println(e.toString());
                }
              }
            }
      }
    };
    initTerminal();
  }, []);

  return (
    <div className="flex flex-col min-h-screen bg-white dark:bg-gray-900 text-black dark:text-white">
      <div className="flex-grow flex flex-col items-center justify-start p-1 sm:p-2">
        <main className="flex flex-col gap-2 w-full max-w-4xl mt-2 sm:mt-4">
          <h1 className="text-2xl font-bold text-center">{t('title')}</h1>
          <div className="w-full h-[50vh] sm:h-[70vh] bg-black rounded">
            <XTerm ref={xtermRef} />
          </div>
        </main>
      </div>
    </div>
  );
}
