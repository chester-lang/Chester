export const SUPPORTED_LOCALES = ['en', 'fr', 'zh-tw', 'zh-sg'] as const
export type SupportedLocale = (typeof SUPPORTED_LOCALES)[number]