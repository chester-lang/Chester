'use client';

import { useTranslations } from 'next-intl';

export default function DownloadPage() {
    const t = useTranslations('DownloadPage');

    return (
        <div className="flex flex-col min-h-screen">
            <div className="flex-grow flex flex-col items-center justify-center p-4 pb-8 gap-8 sm:p-8 font-[family-name:var(--font-geist-sans)]">
                <main className="flex flex-col gap-6 w-full max-w-4xl">
                    <h1 className="text-2xl font-bold text-center">{t('title')}</h1>
                    <p className="text-center">{t('introText')}</p>
                    <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                        <DownloadButton platform="Windows" url='https://example.com/chester-windows.exe' />
                        <DownloadButton platform="macOS" url='https://example.com/chester-macos.dmg' />
                        <DownloadButton platform="Linux" url='https://example.com/chester-linux.tar.gz' />
                        <DownloadButton platform="Source Code" url='https://github.com/chester-lang/chester' />
                    </div>
                </main>
            </div>
        </div>
    );
}

function DownloadButton({ platform, url }: { platform: string, url: string }) {
    const t = useTranslations('DownloadPage');
    return (
        <a
            href={url}
            className="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded text-center"
            download
        >
            {t('downloadFor', { platform })}
        </a>
    );
}
