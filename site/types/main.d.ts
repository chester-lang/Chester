import { Terminal } from '@xterm/xterm';

export function startRepl(terminal: Terminal): Promise<void>;
/* eslint-disable @typescript-eslint/no-explicit-any */
export function startReplPty(pty: any): Promise<void>;