import * as path from 'path';
import * as vscode from 'vscode';
import {
  workspace,
  ExtensionContext,
  languages,
  window,
  commands,
} from 'vscode';
import {
  LanguageClient,
  LanguageClientOptions,
  ServerOptions,
  TransportKind,
} from 'vscode-languageclient/node';

let client: LanguageClient;

export function activate(context: ExtensionContext) {
  // Path to the server JAR
  const serverJar = context.asAbsolutePath(
    path.join('server', 'chester-lsp.jar')
  );

  // Debug options for the server
  const debugOptions = [
    '-Xdebug',
    '-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=1044',
  ];

  // Server options: run and debug modes
  const serverOptions: ServerOptions = {
    run: {
      command: 'java',
      args: ['-jar', serverJar],
      transport: TransportKind.ipc,
    },
    debug: {
      command: 'java',
      args: [...debugOptions, '-jar', serverJar],
      transport: TransportKind.ipc,
    },
  };

  // Client options
  const clientOptions: LanguageClientOptions = {
    documentSelector: [{ scheme: 'file', language: 'chester' }],
    synchronize: {
      fileEvents: workspace.createFileSystemWatcher('**/*.chester'),
    },
  };

  // Create the language client and start the client
  client = new LanguageClient(
    'chesterLanguageServer',
    'Chester Language Server',
    serverOptions,
    clientOptions
  );

  client.start();

  client.onDidChangeState((event) => {
    if (event.newState === 2) {
      // Connection is now active
    }
  });

  client.onReady().then(() => {
    // Client is ready
  });
}

export function deactivate(): Thenable<void> | undefined {
  if (!client) {
    return undefined;
  }
  return client.stop();
}