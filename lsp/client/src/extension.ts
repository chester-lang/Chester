import * as path from 'path';
import {
  workspace,
  ExtensionContext,
  window,
  WorkspaceConfiguration,
} from 'vscode';
import {
  LanguageClient,
  LanguageClientOptions,
  StreamInfo,
} from 'vscode-languageclient/node';
import util from 'node:util';
import { exec as execCallback, spawn } from 'child_process';
import * as net from 'net';
import * as fs from 'fs';

const exec = util.promisify(execCallback);

let client: LanguageClient | undefined;
let serverProcess: any;

export async function activate(context: ExtensionContext) {
  console.log('Chester Language Server is now active!');

  // Read the 'chester.java.home' setting
  const config: WorkspaceConfiguration = workspace.getConfiguration('chester');
  let javaHome: string | undefined = config.get('java.home');

  // Determine the Java executable path
  let javaExecutable = 'java';
  if (javaHome && javaHome.trim() !== '') {
    javaExecutable = path.join(javaHome, 'bin', 'java');

    // Ensure the path is correctly formatted for the OS
    if (process.platform === 'win32') {
      javaExecutable += '.exe';
    }
  }

  try {
    // Check if Java is available and print its version
    console.log(`Using Java executable: ${javaExecutable}`);
    const { stdout, stderr } = await exec(`"${javaExecutable}" -version`);
    if (stderr) {
      console.log(`Java STDERR: ${stderr}`);
    }
    if (stdout) {
      console.log(`Java STDOUT: ${stdout}`);
    }
  } catch (error) {
    console.error('Error while checking Java version:', error);
    window.showErrorMessage(
      'Java Runtime Environment not found. Please install Java or set the "chester.java.home" setting.'
    );
    return;
  }

  try {
    // Path to the server JAR inside the extension
    const serverJar = context.asAbsolutePath(
      path.join('server', 'chester-lsp.jar')
    );

    // Verify server JAR exists
    if (!fs.existsSync(serverJar)) {
      window.showErrorMessage(
        `Language server JAR not found at ${serverJar}. Please ensure the server is built correctly.`
      );
      return;
    } else {
      console.log(`Language server JAR found at ${serverJar}`);
    }

    // Start the server process using the configured Java executable
    serverProcess = spawn(`"${javaExecutable}"`, ['-jar', serverJar, '1044']);

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

    // Wait for the server to start
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
    serverProcess = undefined;
  }
  if (client) {
    const result = client.stop();
    client = undefined;
    return result;
  }
  return undefined;
}