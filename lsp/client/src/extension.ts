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
} from 'vscode-languageclient/node';

let client: LanguageClient;

export function activate(context: ExtensionContext) {
  // Path to the server JAR
  const serverJar = context.asAbsolutePath(
    path.join('server', 'chester-lsp.jar')
  );

  // Server options
  const serverOptions: ServerOptions = {
    command: 'java',
    args: ['-jar', serverJar],
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
}

export function deactivate(): Thenable<void> | undefined {
  if (!client) {
    return undefined;
  }
  return client.stop();
}