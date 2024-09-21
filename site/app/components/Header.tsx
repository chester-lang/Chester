import Link from 'next/link';
import Image from 'next/image';
import { useState } from 'react';

const Header = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  return (
    <header className="flex flex-wrap items-center justify-between p-4 bg-gray-100 dark:bg-gray-800">
      <div className="flex items-center">
        <Image
          src="/chester-logo.svg"
          alt="Chester logo"
          width={40}
          height={40}
          className="mr-4 dark:invert"
        />
        <Link href="/" className="text-xl font-bold">
          Chester
        </Link>
      </div>
      <button 
        className="md:hidden"
        onClick={() => setIsMenuOpen(!isMenuOpen)}
      >
        {isMenuOpen ? 'Close' : 'Menu'}
      </button>
      <nav className={`w-full md:w-auto ${isMenuOpen ? 'block' : 'hidden'} md:block mt-4 md:mt-0`}>
        <ul className="flex flex-col md:flex-row space-y-2 md:space-y-0 md:space-x-6">
          <li><Link href="/" className="hover:underline">Home</Link></li>
          <li><Link href="/download" className="hover:underline">Download</Link></li>
          <li><Link href="/get-started" className="hover:underline">Get Started</Link></li>
          <li><Link href="/playground" className="hover:underline">Playground</Link></li>
          <li><Link href="/docs" className="hover:underline">Documentation</Link></li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
