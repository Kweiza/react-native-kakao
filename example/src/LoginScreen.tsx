import * as React from 'react';

import { StyleSheet, View, Button } from 'react-native';
import { KakaoLogin } from 'react-native-kakao';

export default function LoginScreen() {
  return (
    <View style={styles.container}>
      <Button
        title={'isKakaoTalkLoginAvailable'}
        onPress={async () => {
          try {
            const r = await KakaoLogin.isKakaoTalkLoginAvailable();
            console.log(r);
          } catch (e) {
            console.log(e);
          }
        }}
      />

      <Button
        title={'loginWithKakaoTalk'}
        onPress={async () => {
          try {
            const r = await KakaoLogin.loginWithKakaoTalk();
            console.log(r);
          } catch (e) {
            console.log(e);
          }
        }}
      />

      <Button
        title={'loginWithKakaoAccount'}
        onPress={async () => {
          try {
            const r = await KakaoLogin.loginWithKakaoAccount();
            console.log(r);
          } catch (e) {
            console.log(e);
          }
        }}
      />

      <Button
        title={'login'}
        onPress={async () => {
          try {
            const r = await KakaoLogin.login();
            console.log(r);
          } catch (e) {
            console.log(e);
          }
        }}
      />

      <Button
        title={'logout'}
        onPress={async () => {
          try {
            const r = await KakaoLogin.logout();
            console.log(r);
          } catch (e) {
            console.log(e);
          }
        }}
      />

      <Button
        title={'unlink'}
        onPress={async () => {
          try {
            const r = await KakaoLogin.unlink();
            console.log(r);
          } catch (e) {
            console.log(e);
          }
        }}
      />

      <Button
        title={'accessToken'}
        onPress={async () => {
          try {
            const r = await KakaoLogin.accessTokenInfo();
            console.log(r);
          } catch (e) {
            console.log(e);
          }
        }}
      />

      <Button
        title={'me'}
        onPress={async () => {
          try {
            const r = await KakaoLogin.me();
            console.log(r);
          } catch (e) {
            console.log(e);
          }
        }}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
