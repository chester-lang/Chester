'use client';

import Image from "next/image";
import dynamic from 'next/dynamic';

const Repl = dynamic(() => import('./components/Repl'), {
    ssr: false
})

export default function Home() {
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
        <Repl />
      </main>
      <footer className="row-start-3 flex gap-6 flex-wrap items-center justify-center">
        {/* Footer content remains the same */}
      </footer>
    </div>
  );
}
