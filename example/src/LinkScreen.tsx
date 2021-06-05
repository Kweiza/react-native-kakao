import * as React from 'react';

import { StyleSheet, View, Button, ScrollView, Text } from 'react-native';
import { KakaoLink } from 'react-native-kakao';
import type {
  TKakaoMessageTemplateCommerce,
  TKakaoMessageTemplateFeed,
  TKakaoMessageTemplateList,
  TKakaoMessageTemplateLocation,
  TKakaoMessageTemplateText,
} from 'src/KakaoMessageTemplate';

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
        android_execution_params: { key1: 'value1', key2: 'value2' },
        ios_execution_params: { key1: 'value1', key2: 'value2' },
      },
    },
  ],
};

const templateList: TKakaoMessageTemplateList = {
  object_type: 'list',
  header_title: 'WEEKLY MAGAZINE',
  header_link: {
    mobile_web_url: 'https://developers.kakao.com',
    web_url: 'https://developers.kakao.com',
  },
  contents: [
    {
      title: '취미의 특징, 탁구',
      description: '스포츠',
      image_url:
        'http://mud-kage.kakao.co.kr/dn/bDPMIb/btqgeoTRQvd/49BuF1gNo6UXkdbKecx600/kakaolink40_original.png',
      link: {
        mobile_web_url: 'https://developers.kakao.com',
        web_url: 'https://developers.kakao.com',
      },
    },
    {
      title: '크림으로 이해하는 커피이야기',
      description: '음식',
      image_url:
        'http://mud-kage.kakao.co.kr/dn/QPeNt/btqgeSfSsCR/0QJIRuWTtkg4cYc57n8H80/kakaolink40_original.png',
      link: {
        mobile_web_url: 'https://developers.kakao.com',
        web_url: 'https://developers.kakao.com',
      },
    },
    {
      title: '감성이 가득한 분위기',
      description: '사진',
      image_url:
        'http://mud-kage.kakao.co.kr/dn/c7MBX4/btqgeRgWhBy/ZMLnndJFAqyUAnqu4sQHS0/kakaolink40_original.png',
      link: {
        mobile_web_url: 'https://developers.kakao.com',
        web_url: 'https://developers.kakao.com',
      },
    },
  ],
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
        android_execution_params: { key1: 'value1', key2: 'value2' },
        ios_execution_params: { key1: 'value1', key2: 'value2' },
      },
    },
  ],
};

const templateLocation: TKakaoMessageTemplateLocation = {
  object_type: 'location',
  address_title: '카카오 판교오피스 카페톡',
  address: '경기 성남시 분당구 판교역로 235 에이치스퀘어 N동 8층',
  content: {
    description: '이번 주는 체리블라썸라떼 1+1',
    image_url:
      'http://mud-kage.kakao.co.kr/dn/bSbH9w/btqgegaEDfW/vD9KKV0hEintg6bZT4v4WK/kakaolink40_original.png',
    link: {
      mobile_web_url: 'https://developers.kakao.com',
      web_url: 'https://developers.com',
    },
    title: '신메뉴 출시❤️ 체리블라썸라떼',
  },
  social: {
    comment_count: 45,
    like_count: 286,
    shared_count: 845,
  },
};

const templateCommerce: TKakaoMessageTemplateCommerce = {
  object_type: 'commerce',
  commerce: {
    product_name: 'Ivory long dress',
    regular_price: 208800,
    discount_price: 146160,
    discount_rate: 30,
  },
  content: {
    title: 'Ivory long dress (4 Color)',
    image_url:
      'http://mud-kage.kakao.co.kr/dn/RY8ZN/btqgOGzITp3/uCM1x2xu7GNfr7NS9QvEs0/kakaolink40_original.png',
    link: {
      mobile_web_url: 'https://developers.kakao.com',
      web_url: 'https://developers.kakao.com',
    },
  },
  buttons: [
    {
      title: '구매하기',
      link: {
        mobile_web_url: 'https://developers.kakao.com',
        web_url: 'https://developers.kakao.com',
      },
    },
    {
      title: '공유하기',
      link: {
        android_execution_params: { key1: 'value1', key2: 'value2' },
        ios_execution_params: { key1: 'value1', key2: 'value2' },
      },
    },
  ],
};

const templateText: TKakaoMessageTemplateText = {
  object_type: 'text',
  text: '(text)',
  link: {
    web_url: 'http://dev.kakao.com',
    mobile_web_url: 'http://dev.kakao.com',
  },
  button_title: '바로 확인',
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
        <Button
          title={'listLink'}
          onPress={async () => {
            try {
              const r = await KakaoLink.listLink(templateList);
              console.log(r);
              setLog(JSON.stringify(r, null, 2));
            } catch (e) {
              console.log(e);
            }
          }}
        />
        <Button
          title={'locationLink'}
          onPress={async () => {
            try {
              const r = await KakaoLink.locationLink(templateLocation);
              console.log(r);
              setLog(JSON.stringify(r, null, 2));
            } catch (e) {
              console.log(e);
            }
          }}
        />
        <Button
          title={'commerceLink'}
          onPress={async () => {
            try {
              const r = await KakaoLink.commerceLink(templateCommerce);
              console.log(r);
              setLog(JSON.stringify(r, null, 2));
            } catch (e) {
              console.log(e);
            }
          }}
        />
        <Button
          title={'textLink'}
          onPress={async () => {
            try {
              const r = await KakaoLink.textLink(templateText);
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
