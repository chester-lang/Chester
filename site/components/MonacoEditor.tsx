'use client';

import { Editor, OnMount, OnChange } from '@monaco-editor/react';

interface MonacoEditorProps {
    code?: string;
    onChange?: OnChange;
    onMount?: OnMount;
}

export default function MonacoEditor({ code, onChange, onMount }: MonacoEditorProps) {
    return (
        <Editor
            height="400px"
            defaultLanguage="javascript"
            value={code}
            theme="vs-dark"
            options={{
                minimap: { enabled: false },
                automaticLayout: true,
            }}
            onMount={onMount}
            onChange={onChange}
        />
    );
}