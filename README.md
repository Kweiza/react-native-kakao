# react-native-kakao

KakaoSDK for react native

## Installation

```sh
yarn add @kweiza/react-native-kakao
```

```sh
npx pod-install
or
cd ios && pod install
```

## Configuration

### iOS

- iOS Deployment Target을 11.0로 변경

- Swift Bridge 생성

- info.plist에 아래 부분 추가

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

### Android

- android/build.gradle에 아래 내용 추가

```
...
buildscript {
  ext {
    ...
    kotlinVersion = '1.3.41'
    ...
  }
  ...
  dependencies {
    ...
    classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
  }
}
...

allprojects {
    repositories {
      ...
      maven { url 'https://devrepo.kakao.com/nexus/content/groups/public/' }
    }
}
```

- android/app/src/main/AndroidManifest.xml 에 아래 내용 추가

```
<manifest
  ...
>
  ...
  <application
    android:allowBackup="true"
    tools:replace="android:allowBackup" // android:allowBackup="true" 추가 시 에러가 난다면 이 라인을 추가
    ...
  >
  ...
    <activity android:name="com.kakao.sdk.auth.AuthCodeHandlerActivity">
      <intent-filter>
          <action android:name="android.intent.action.VIEW" />
          <category android:name="android.intent.category.DEFAULT" />
          <category android:name="android.intent.category.BROWSABLE" />

          <!-- Redirect URI: "kakao{NATIVE_APP_KEY}://oauth“ -->
          <data android:host="oauth"
              android:scheme="kakao{카카오 네이티브앱 키}" />
      </intent-filter>
    </activity>
  </application>
</manifest>
...
```

- android/app/src/main/res/values/strings.xml 에 아래 내용 추가
```
<resources>
  ...
  <string name="kakao_app_key">{카카오 네이티브앱 키}</string>
</resources>
```

## Usage

### KakaoLogin

자세한 예제는 [example/src/LoginScreen.tsx](./example/src/LoginScreen.tsx) 참고

```js
import { KakaoLogin } from 'react-native-kakao';

try {
  const r = await KakaoLogin.login();
  console.log(r);
} catch (e) {
  console.log(e);
}
// ...
```

- methods
```ts
// 카카오톡 설치 여부
// response
// boolean
isKakaoTalkLoginAvailable()

// 카카오톡 앱 로그인
// response
// {
//   accessToken: string;
//   expiredAt: Date;
//   refreshToken: string;
//   refreshTokenExpiresAt: Date;
//   scopes: string[];
// }
loginWithKakaoTalk()

// 카카오계정 로그인 (웹브라우저)
// response
// {
//   accessToken: string;
//   expiredAt: Date;
//   refreshToken: string;
//   refreshTokenExpiresAt: Date;
//   scopes: string[];
// }
loginWithKakaoAccount()

// 카카오 로그인 - 카톡앱 로그인, 앱 없으면 웹으로 연결
// response
// {
//   accessToken: string;
//   expiredAt: Date;
//   refreshToken: string;
//   refreshTokenExpiresAt: Date;
//   scopes: string[];
// }
login()

// 로그아웃
logout()

// 카카오 계정 연결 해제
unlink()

// 토큰 정보
// response
// {
//   appId: number;
//   id?: number;
//   expiresIn: number;
// }
accessTokenInfo()

// 로그인 유저 정보
// response
// {
//   id?: number;
//   properties?: TKakaoUserProperties;
//   kakaoAccount?: TKakaoUserKakaoAccount;
//   groupUserToken?: string;
//   connectedAt?: Date;
//   synchedAt?: Date;
//   hasSignedUp?: boolean;
// }
me()
```

### KakaoLink

자세한 예제는 [example/src/LinkScreen.tsx](./example/src/LinkScreen.tsx) 참고

```js
import { KakaoLink } from 'react-native-kakao';

try {
  await KakaoLogin.feedLink(template);
} catch (e) {
  console.log(e);
}
// ...
```

```ts
// 참고 https://developers.kakao.com/docs/latest/ko/message/message-template
// 공통 resposne
// {
//   argumentMsg: {[key: string]: string};
//   warningMsg: {[key: string]: string};
// }

feedLink(template: TKakaoMessageTemplateFeed)
listLink(template: TKakaoMessageTemplateList)
locationLink(template: TKakaoMessageTemplateLocation)
commerceLink(template: TKakaoMessageTemplateCommerce)
textLink(template: TKakaoMessageTemplateText)
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
