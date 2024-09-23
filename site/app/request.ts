import deepmerge from 'deepmerge';
import { notFound } from 'next/navigation';
import {getRequestConfig} from 'next-intl/server';
 
export default getRequestConfig(async () => {
    let locale = 'en'
 
  return {
    locale: locale,
    messages: await getMessages(locale)
  };
});

//function to get the translations
async function getMsg(locale: string) {
    try {
      return (await import(`../messages/${locale}.json`)).default
    } catch (error) {
      notFound()
    }
  }
  async function getMessages(locale: string) {
    let result = await getMsg(locale)
    if(locale.startsWith('zh')){
      result = deepmerge(deepmerge(await getMsg('zh-tw'), await getMsg('zh-sg')), result)
    }
    if(locale == 'en'){
      return result
    }
    return deepmerge(await getMsg('en'), result)
}