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
  console.log('Chester Language Server is now active!');

  const serverJar = context.asAbsolutePath(path.join('server', 'chester-lsp.jar'));

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

  client.onReady().then(() => {
    console.log('Chester Language Server is ready');
  }).catch((error) => {
    console.error('Failed to start Chester Language Server', error);
    window.showErrorMessage('Failed to start Chester Language Server. Check the console for more information.');
  });

  const disposable = client.start();
  context.subscriptions.push(disposable);

  client.onDidChangeState((event) => {
    if (event.newState === 1) {
      console.log('Chester Language Server is starting');
    } else if (event.newState === 2) {
      console.log('Chester Language Server connection is active');
    } else if (event.newState === 3) {
      console.log('Chester Language Server connection is closing');
    } else if (event.newState === 4) {
      console.log('Chester Language Server connection is closed');
    }
  });
}

export function deactivate(): Thenable<void> | undefined {
  console.log('Deactivating Chester Language Server');
  if (!client) {
    return undefined;
  }
  return client.stop();
}