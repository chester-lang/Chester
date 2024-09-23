import deepmerge from 'deepmerge';
import { notFound } from 'next/navigation';

export async function getMsg(locale: string) {
  try {
    return (await import(`../messages/${locale}.json`)).default
  } catch (error) {
    notFound()
  }
}

export async function getMessages(locale: string) {
  let result = await getMsg(locale)
  if(locale.startsWith('zh')){
    result = deepmerge(deepmerge(await getMsg('zh-tw'), await getMsg('zh-sg')), result)
  }
  if(locale == 'en-nz'){
    return result
  }
  return deepmerge(await getMsg('en-nz'), result)
}

export const SUPPORTED_LOCALES = ['en-nz', 'fr', 'zh-tw', 'zh-sg', 'de'] as const
export type SupportedLocale = (typeof SUPPORTED_LOCALES)[number]