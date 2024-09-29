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
  StreamInfo,
} from 'vscode-languageclient/node';
import util from 'node:util';
import { exec as execCallback, spawn } from 'child_process';
import * as net from 'net';

const exec = util.promisify(execCallback);
let client: LanguageClient;
let serverProcess: any;

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
    } else {
      console.log(`Language server JAR found at ${serverJar}`);
    }

    // Start the server process
    serverProcess = spawn('java', ['-jar', serverJar, '1044']);

    // Listen to server output
    serverProcess.stdout.on('data', (data: Buffer) => {
      console.log(`Server STDOUT: ${data.toString()}`);
    });

    serverProcess.stderr.on('data', (data: Buffer) => {
      console.error(`Server STDERR: ${data.toString()}`);
    });

    serverProcess.on('error', (error: Error) => {
      console.error('Server process error:', error);
    });

    serverProcess.on('exit', (code: number, signal: string) => {
      console.log(`Server process exited with code ${code}, signal ${signal}`);
    });

    // Optionally wait for the server to start
    await new Promise((resolve) => setTimeout(resolve, 2000));

    // Server options to connect to the server started above
    const serverOptions = () => {
      const socket = net.connect({ port: 1044 });
      const result: StreamInfo = {
        reader: socket,
        writer: socket,
      };
      return Promise.resolve(result);
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
  if (serverProcess) {
    serverProcess.kill();
  }
  if (!client) {
    return undefined;
  }
  return client.stop();
}