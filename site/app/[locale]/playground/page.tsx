'use client';

import { useRef } from 'react';
import { useTranslations } from 'next-intl';
import MonacoEditor from '@/components/MonacoEditor';
import * as monaco from 'monaco-editor';

export default function PlaygroundPage() {
    const t = useTranslations('PlaygroundPage');
    const editorRef = useRef<monaco.editor.IStandaloneCodeEditor | null>(null);

    function handleEditorDidMount(editor: monaco.editor.IStandaloneCodeEditor) {
        editorRef.current = editor;
    }

    function runCode() {
        if (editorRef.current) {
            const code = editorRef.current.getValue();
            // Here you would typically send the code to a backend for execution
            // For now, we'll just display the code in the output area
            const outputElement = document.getElementById('output');
            if (outputElement) {
                outputElement.textContent = `Running code:\n\n${code}`;
            }
        }
    }

    return (
        <div className="flex flex-col min-h-screen">
            <div className="flex-grow flex flex-col items-center justify-center p-4 pb-8 gap-8 sm:p-8 font-[family-name:var(--font-geist-sans)]">
                <main className="flex flex-col gap-6 w-full max-w-4xl">
                    <h1 className="text-2xl font-bold text-center">{t('title')}</h1>
                    <div className="flex flex-col md:flex-row gap-4">
                        <div className="w-full md:w-1/2">
                            <MonacoEditor onMount={handleEditorDidMount} />
                        </div>
                        <div className="w-full md:w-1/2 bg-black text-white p-4 rounded">
                            <h2 className="text-xl font-bold mb-2">{t('output')}</h2>
                            <pre id="output" className="whitespace-pre-wrap"></pre>
                        </div>
                    </div>
                    <button 
                        className="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded"
                        onClick={runCode}
                    >
                        {t('runCode')}
                    </button>
                </main>
            </div>
        </div>
    );
}