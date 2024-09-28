import * as assert from 'assert';
import * as vscode from 'vscode';

suite('Extension Test Suite', () => {
  test('Sample test', async () => {
    const doc = await vscode.workspace.openTextDocument({ language: 'chester', content: 'test content' });
    assert.strictEqual(doc.languageId, 'chester');
  });
});
