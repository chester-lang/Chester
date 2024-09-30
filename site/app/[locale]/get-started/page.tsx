'use client';

import { useTranslations } from 'next-intl';

export default function GetStartedPage() {
    const t = useTranslations('GetStartedPage');

    return (
        <main className="flex-grow flex flex-col items-center justify-center p-4 pb-8 gap-8 sm:p-8">
            <div className="flex flex-col gap-6 w-full max-w-4xl">
                <h1 className="text-2xl font-bold text-center">{t('title')}</h1>
                <p className="text-center">{t('introText')}</p>
            </div>
        </main>
    );
}
