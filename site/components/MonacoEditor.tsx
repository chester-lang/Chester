'use client';

import { Editor, OnMount } from '@monaco-editor/react';

interface MonacoEditorProps {
    onMount?: OnMount;
}

export default function MonacoEditor({ onMount }: MonacoEditorProps) {
    return (
        <Editor
            height="400px"
            defaultLanguage="javascript"
            defaultValue="// Write your Chester code here"
            theme="vs-dark"
            options={{
                minimap: { enabled: false },
                automaticLayout: true,
            }}
            onMount={onMount}
        />
    );
}