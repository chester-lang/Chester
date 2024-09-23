'use client';

import { useTranslations } from 'next-intl';

export default function Footer() {
  const t = useTranslations('Footer');

  return (
    <footer className="mt-auto">
      <p className="text-sm text-center">
        {t('footer', { year: new Date().getFullYear() })}
      </p>
    </footer>
  );
}