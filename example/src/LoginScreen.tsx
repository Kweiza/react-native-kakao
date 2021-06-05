import * as React from 'react';

import { StyleSheet, View, Button, Text, ScrollView } from 'react-native';
import { KakaoLogin } from 'react-native-kakao-sdk';

export default function LoginScreen() {
  const [log, setLog] = React.useState('');
  return (
    <View style={styles.container}>
      <ScrollView>
        <Text>{log}</Text>
        <Button
          title={'isKakaoTalkLoginAvailable'}
          onPress={async () => {
            try {
              const r = await KakaoLogin.isKakaoTalkLoginAvailable();
              console.log(r);
              setLog(JSON.stringify(r, null, 2));
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
              setLog(JSON.stringify(r, null, 2));
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
              setLog(JSON.stringify(r, null, 2));
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
              setLog(JSON.stringify(r, null, 2));
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
              setLog(JSON.stringify(r, null, 2));
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
              setLog(JSON.stringify(r, null, 2));
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
              setLog(JSON.stringify(r, null, 2));
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
              setLog(JSON.stringify(r, null, 2));
            } catch (e) {
              console.log(e);
            }
          }}
        />
      </ScrollView>
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
