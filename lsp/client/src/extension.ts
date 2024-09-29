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
    // Check if Java is available and print its version
    const { stdout, stderr } = await exec('java -version');

    if (stdout) {
      console.log(`Java STDOUT: ${stdout}`);
    }

    if (stderr) {
      console.log(`Java STDERR: ${stderr}`);
    }
  } catch (error) {
    console.error('Error while checking Java version:', error);
    window.showErrorMessage(
      'Java Runtime Environment is not installed or not added to PATH. Please install Java to use the Chester Language Server.'
    );
    return;
  }

  try {
    // Path to the server JAR inside the extension
    const serverJar = context.asAbsolutePath(
      path.join('server', 'chester-lsp.jar')
    );

    // Verify server JAR exists
    const fs = require('fs');
    if (!fs.existsSync(serverJar)) {
      window.showErrorMessage(
        `Language server JAR not found at ${serverJar}. Please ensure the server is built correctly.`
      );
      return;
    }

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
    console.error('Failed to start Chester Language Server', error);
    window.showErrorMessage(
      'Failed to start Chester Language Server. Check the console for more information.'
    );
  }
}

export function deactivate(): Thenable<void> | undefined {
  console.log('Deactivating Chester Language Server');
  if (!client) {
    return undefined;
  }
  return client.stop();
}