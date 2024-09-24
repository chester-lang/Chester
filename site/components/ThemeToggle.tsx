
   'use client';

   import { useEffect, useState } from 'react';

   export default function ThemeToggle() {
     const [theme, setTheme] = useState('light');

     useEffect(() => {
       if (window.matchMedia('(prefers-color-scheme: dark)').matches) {
         setTheme('dark');
       } else {
         setTheme('light');
       }
     }, []);

     useEffect(() => {
       if (theme === 'dark') {
         document.documentElement.classList.add('dark');
       } else {
         document.documentElement.classList.remove('dark');
       }
     }, [theme]);

     const toggleTheme = () => {
       setTheme(theme === 'dark' ? 'light' : 'dark');
     };

     return (
       <button onClick={toggleTheme}>
         {theme === 'dark' ? 'Switch to Light Mode' : 'Switch to Dark Mode'}
       </button>
     );
   }