import Link from 'next/link';
import Image from 'next/image';

const Header = () => {
  return (
    <header className="flex items-center justify-between p-4 bg-gray-100 dark:bg-gray-800">
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
      <nav>
        <ul className="flex space-x-6">
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
