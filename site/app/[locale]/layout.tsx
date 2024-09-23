// app/[locale]/layout.tsx

import '../globals.css'
import type { Metadata } from 'next'
import Header from '@/components/Header'
import { ReactNode } from 'react'
import { notFound } from 'next/navigation'
import { NextIntlClientProvider } from 'next-intl'
import { SUPPORTED_LOCALES } from '@/components/locales';
import deepmerge from 'deepmerge';

type Props = {
  children: ReactNode
  params: { locale: string }
}


export const metadata: Metadata = {
  title: "Chester",
  description: "Chester: A Programming Language",
}

//function to get the translations
async function getMsg(locale: string) {
  try {
    return (await import(`../../messages/${locale}.json`)).default
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

//function to generate the routes for all the locales
export async function generateStaticParams() {
  return SUPPORTED_LOCALES.map((locale) => ({ locale }))
}



export default async function RootLayout({
  children,
  params: { locale },
}: Props) {
  const messages = await getMessages(locale)

  return (
    <html lang={locale}>
      <body className='bg-gray-100'>
        <NextIntlClientProvider locale={locale} messages={messages}>
          <Header />
          <div className='p-5'>
            {children}
          </div>
        </NextIntlClientProvider>
      </body>
    </html>
  )
}
