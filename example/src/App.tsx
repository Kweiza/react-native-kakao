import * as React from 'react';

import { StyleSheet, View, Button } from 'react-native';
import { KakaoLogin } from 'react-native-kakao';

export default function App() {
  return (
    <View style={styles.container}>
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
