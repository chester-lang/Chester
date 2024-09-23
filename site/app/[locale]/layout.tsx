// app/[locale]/layout.tsx

import '../globals.css'
import type { Metadata } from 'next'
import Header from '@/components/Header'
import Footer from '@/components/Footer'
import { ReactNode } from 'react'
import { notFound } from 'next/navigation'
import { NextIntlClientProvider } from 'next-intl'
import { SUPPORTED_LOCALES } from '@/i18n';
import deepmerge from 'deepmerge';
import { getMessages } from '@/i18n'
import { createTranslator } from 'next-intl'

type Props = {
  children: ReactNode
  params: { locale: string }
}

export async function generateMetadata({ params: { locale } }: Props): Promise<Metadata> {
  const messages = await getMessages(locale)

  return {
    title: messages.metadata.title,
    description: messages.metadata.description,
  }
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
          <Footer />
        </NextIntlClientProvider>
      </body>
    </html>
  )
}
