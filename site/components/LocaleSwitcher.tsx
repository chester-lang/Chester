// components/LocaleSwitcher.tsx

'use client'
import { useLocale, useTranslations } from 'next-intl';
import { usePathname, useRouter } from './navigation';
import { SUPPORTED_LOCALES, SupportedLocale } from './locales';

export default function LocaleSwitcher() {
    const t = useTranslations('LocaleSwitcher')
    const locale = useLocale();
    const router = useRouter();
    const pathname = usePathname();

    const onLocaleChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const newLocale = e.target.value;
        router.replace(pathname, { locale: newLocale as SupportedLocale });
    }

    return (
        <select
            defaultValue={locale}
            onChange={onLocaleChange}
        >
            {SUPPORTED_LOCALES.map((lang) => (
                <option key={lang} value={lang}>
                    {t('locale', { locale: lang.replace('-', '_') })}
                </option>
            ))}
        </select>
    )
}