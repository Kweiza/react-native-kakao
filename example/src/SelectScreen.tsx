import { useNavigation } from '@react-navigation/core';
import * as React from 'react';

import { StyleSheet, View, Button } from 'react-native';

export default function SelectScreen() {
  const { navigate } = useNavigation();
  return (
    <View style={styles.container}>
      <Button
        title={'Login'}
        onPress={() => {
          navigate('LoginScreen');
        }}
      />
      <Button
        title={'Link'}
        onPress={() => {
          navigate('LinkScreen');
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
