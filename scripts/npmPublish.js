const child_process = require('child_process');

const { name, ...pak } = require('../package.json');

const path = require('path');
const fs = require('fs');
const root = path.resolve(__dirname, '..');
fs.writeFileSync(
  path.resolve(root, 'package.json'),
  JSON.stringify({ name: '@kweiza/react-native-kakao', ...pak }, null, 2)
);

const options = {
  cwd: process.cwd(),
  env: process.env,
  stdio: 'inherit',
  encoding: 'utf-8',
};
result = child_process.execSync('npm publish', [], options);

fs.writeFileSync(
  path.resolve(root, 'package.json'),
  JSON.stringify({ name: 'react-native-kakao', ...pak }, null, 2)
);
