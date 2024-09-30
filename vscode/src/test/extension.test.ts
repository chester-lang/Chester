import * as assert from 'assert';
import * as vscode from 'vscode';

suite('Extension Test Suite', () => {
  test('Activation Test', async () => {
    const extension = vscode.extensions.getExtension('your-publisher-name.chester-language-support');
    assert.ok(extension);
    await extension!.activate();
    assert.strictEqual(extension!.isActive, true);
  });

  test('Language Recognition Test', async () => {
    const doc = await vscode.workspace.openTextDocument({ language: 'chester', content: 'test content' });
    assert.strictEqual(doc.languageId, 'chester');
  });
});
