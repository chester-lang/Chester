import {createSharedPathnamesNavigation} from 'next-intl/navigation';
import { SUPPORTED_LOCALES } from './locales';
const locales = SUPPORTED_LOCALES;
export const {Link, useRouter, usePathname, redirect} = createSharedPathnamesNavigation({locales});