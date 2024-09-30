'use client';

import { useTranslations } from 'next-intl';

export default function DownloadPage() {
    const t = useTranslations('DownloadPage');

    return (
        <main className="flex-grow flex flex-col items-center justify-center p-4 pb-8 gap-8 sm:p-8">
            <div className="flex flex-col gap-6 w-full max-w-4xl">
                <h1 className="text-2xl font-bold text-center">{t('title')}</h1>
                <p className="text-center">{t('introText')}</p>
                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                    <DownloadButton
                        platform="Windows"
                        url="https://github.com/chester-lang/chester/releases/download/snapshot-windows/chester.exe"
                        architecture="x86_64"
                    />
                    <DownloadButton
                        platform="macOS"
                        url="https://github.com/chester-lang/chester/releases/download/snapshot-macos/chester"
                        architecture="aarch64"
                    />
                    <DownloadButton
                        platform="Linux"
                        url="https://github.com/chester-lang/chester/releases/download/snapshot-linux/chester"
                        architecture="x86_64"
                    />
                    <DownloadButton
                        platform="Source Code"
                        url="https://github.com/chester-lang/chester"
                        architecture="N/A"
                    />
                </div>
            </div>
        </main>
    );
}

function DownloadButton({ platform, url, architecture }: { platform: string, url: string, architecture: string }) {
    const t = useTranslations('DownloadPage');
    return (
        <a
            href={url}
            className="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded text-center"
            download
        >
            {t('downloadFor', { platform })} - {t('cliSingleFile')} ({architecture})
        </a>
    );
}
