import * as React from 'react';

import { StyleSheet, View, Button, ScrollView, Text } from 'react-native';
import { KakaoLink } from 'react-native-kakao-sdk';
import type { TKakaoMessageTemplateFeed } from 'src/KakaoMessageTemplate';

const templateFeed: TKakaoMessageTemplateFeed = {
  object_type: 'feed',
  content: {
    title: '딸기 치즈 케익',
    description: '#케익 #딸기 #삼평동 #카페 #분위기 #소개팅',
    image_url:
      'http://mud-kage.kakao.co.kr/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png',
    link: {
      mobile_web_url: 'https://developers.kakao.com',
      web_url: 'https://developers.kakao.com',
    },
  },
  social: {
    comment_count: 45,
    like_count: 286,
    shared_count: 845,
  },
  buttons: [
    {
      title: '웹으로 보기',
      link: {
        mobile_web_url: 'https://developers.kakao.com',
        web_url: 'https://developers.kakao.com',
      },
    },
    {
      title: '앱으로 보기',
      link: {
        android_execution_params: 'key1=value1&key2=value2',
        ios_execution_params: 'key1=value1&key2=value2',
      },
    },
  ],
};

export default function LinkScreen() {
  const [log, setLog] = React.useState('');
  return (
    <View style={styles.container}>
      <ScrollView>
        <Text>{log}</Text>
        <Button
          title={'feedLink'}
          onPress={async () => {
            try {
              const r = await KakaoLink.feedLink(templateFeed);
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
