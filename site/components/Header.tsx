import Link from 'next/link';
import Image from 'next/image';
import { useState } from 'react';
import { useTranslation } from 'react-i18next';
// https://stackoverflow.com/questions/65930789/how-does-next-js-basepath-works-for-images/76197261#76197261
import chesterLogo from '../public/chester-logo.svg';

const Header = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const { t } = useTranslation();

  return (
    <header className="flex flex-wrap items-center justify-between p-4 bg-gray-100 dark:bg-gray-800">
      <div className="flex items-center">
        <Image
          src={chesterLogo}
          alt={t('header.logoAlt', 'Chester logo')}
          width={40}
          height={40}
          className="mr-4 dark:invert"
        />
        <Link href="/" className="text-xl font-bold">
          {t('header.title', 'Chester')}
        </Link>
      </div>
      <button 
        className="md:hidden"
        onClick={() => setIsMenuOpen(!isMenuOpen)}
      >
        {isMenuOpen ? t('header.close', 'Close') : t('header.menu', 'Menu')}
      </button>
      <nav className={`w-full md:w-auto ${isMenuOpen ? 'block' : 'hidden'} md:block mt-4 md:mt-0`}>
        <ul className="flex flex-col md:flex-row space-y-2 md:space-y-0 md:space-x-6">
          <li><Link href="/" className="hover:underline">{t('header.home', 'Home')}</Link></li>
          <li><Link href="/download" className="hover:underline">{t('header.download', 'Download')}</Link></li>
          <li><Link href="/get-started" className="hover:underline">{t('header.getStarted', 'Get Started')}</Link></li>
          <li><Link href="/playground" className="hover:underline">{t('header.playground', 'Playground')}</Link></li>
          <li><Link href="/docs" className="hover:underline">{t('header.documentation', 'Documentation')}</Link></li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
