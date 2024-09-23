import {getRequestConfig} from 'next-intl/server';
import { getMessages } from '@/i18n';

export default getRequestConfig(async () => {
    let locale = 'en'

  return {
    locale: locale,
    messages: await getMessages(locale)
  };
});