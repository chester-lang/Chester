import * as path from 'path';
import {
  workspace,
  ExtensionContext,
  window,
} from 'vscode';
import {
  LanguageClient,
  LanguageClientOptions,
  ServerOptions,
} from 'vscode-languageclient/node';
import util from 'node:util';
import { exec as execCallback } from 'node:child_process';

const exec = util.promisify(execCallback);
let client: LanguageClient;

export async function activate(context: ExtensionContext) {
  console.log('Chester Language Server is now active!');

  try {
    // Check if Java is available
    await exec('java -version');

    // Path to the server JAR inside the extension
    const serverJar = context.asAbsolutePath(
      path.join('server', 'chester-lsp.jar')
    );

    // Server options
    const serverOptions: ServerOptions = {
      run: {
        command: 'java',
        args: ['-jar', serverJar],
      },
      debug: {
        command: 'java',
        args: [
          '-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=1044',
          '-jar',
          serverJar,
        ],
      },
    };

    // Client options
    const clientOptions: LanguageClientOptions = {
      documentSelector: [{ scheme: 'file', language: 'chester' }],
      synchronize: {
        fileEvents: workspace.createFileSystemWatcher('**/*.chester'),
      },
    };

    // Create the language client and start it
    client = new LanguageClient(
      'chesterLanguageServer',
      'Chester Language Server',
      serverOptions,
      clientOptions
    );

    // Start the client and handle any errors
    await client.start();
    console.log('Chester Language Server is ready');
  } catch (error: any) {
    if (error.code === 'ENOENT' || error.stderr?.includes('not found')) {
      window.showErrorMessage(
        'Java Runtime Environment is not installed. Please install Java to use the Chester Language Server.'
      );
    } else {
      console.error('Failed to start Chester Language Server', error);
      window.showErrorMessage(
        'Failed to start Chester Language Server. Check the console for more information.'
      );
    }
  }
}

export function deactivate(): Thenable<void> | undefined {
  console.log('Deactivating Chester Language Server');
  if (!client) {
    return undefined;
  }
  return client.stop();
}