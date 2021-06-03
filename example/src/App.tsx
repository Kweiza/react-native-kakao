import * as React from 'react';

import { StyleSheet, View, Text, Button } from 'react-native';
import { KakaoLogin, TKakaoOAuthToken } from 'react-native-kakao';

export default function App() {
  const [result, setResult] = React.useState<TKakaoOAuthToken | undefined>();

  return (
    <View style={styles.container}>
      <Button
        title={'test'}
        onPress={async () => {
          try {
            const r = await KakaoLogin.login();
            setResult(r);
          } catch (e) {
            console.log(e);
          }
        }}
      />
      <Text>Result: {result}</Text>
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
