'use client';

import { redirect } from 'next/navigation';
import { SUPPORTED_LOCALES, SupportedLocale, DEFAULT_LOCALE } from '@/i18n';

export default function RootPage() {
  const preferredLocale = getPreferredLocale();
  redirect(`/${preferredLocale}`);
}

function getPreferredLocale(): SupportedLocale {
  if (typeof navigator === 'undefined') {
    return DEFAULT_LOCALE;
  }

  const languages = navigator.languages || [navigator.language];

  for (const lang of languages) {
    // First, try to find an exact match
    const exactMatch = SUPPORTED_LOCALES.find(locale => 
      locale.toLowerCase() === lang.toLowerCase()
    );
    if (exactMatch) {
      return exactMatch;
    }

    // If no exact match, try to find a match for the language part
    const langPart = lang.split('-')[0].toLowerCase();
    const languageMatch = SUPPORTED_LOCALES.find(locale => 
      locale.toLowerCase().startsWith(langPart)
    );
    if (languageMatch) {
      return languageMatch;
    }
  }

  return DEFAULT_LOCALE;
}