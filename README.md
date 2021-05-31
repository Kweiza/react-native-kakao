# react-native-kakao

KakaoSDK for react native

## Installation

```sh
npm install react-native-kakao
```

## Configuration

```
<key>CFBundleURLTypes</key>
<array>
    <dict>
    <key>CFBundleTypeRole</key>
    <string>Editor</string>
    <key>CFBundleURLSchemes</key>
    <array>
        <string>kakao{카카오 네이티브앱 키}</string>
    </array>
    </dict>
</array>
...
<key>KAKAO_APP_KEY</key>
<string>{카카오 네이티브앱 키}</string>
...
<key>LSApplicationQueriesSchemes</key>
<array>
  <string>kakaokompassauth</string>
  <string>storykompassauth</string>
  <string>kakaolink</string>
</array>
```

## Usage

```js
import Kakao from 'react-native-kakao';

// ...
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
