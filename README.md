# react-native-kakao-sdk

KakaoSDK for react native

## Installation

```sh
yarn add react-native-kakao-sdk
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

### KakaoLogin

자세한 예제는 [example/src/LoginScreen.tsx](./example/src/LoginScreen.tsx) 참고

```js
import { KakaoLogin } from 'react-native-kakao-sdk';

try {
  const r = await KakaoLogin.login();
  console.log(r);
} catch (e) {
  console.log(e);
}
// ...
```

### KakaoLink

자세한 예제는 [example/src/LinkScreen.tsx](./example/src/LinkScreen.tsx) 참고

```js
import { KakaoLink } from 'react-native-kakao-sdk';

try {
  await KakaoLogin.feedLink(template);
} catch (e) {
  console.log(e);
}
// ...
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
