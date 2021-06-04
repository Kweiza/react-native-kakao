import { NativeModules } from 'react-native';
import type {
  TKakaoMessageTemplateCommerce,
  TKakaoMessageTemplateFeed,
  TKakaoMessageTemplateList,
  TKakaoMessageTemplateLocation,
  TKakaoMessageTemplateText,
} from './KakaoMessageTemplate';

export type TKakaoLinkResult = {
  url: string;
};

export type TKakaoLink = {
  feedLink: (template: TKakaoMessageTemplateFeed) => Promise<TKakaoLinkResult>;
  ListLink: (template: TKakaoMessageTemplateList) => Promise<TKakaoLinkResult>;
  LocationLink: (
    template: TKakaoMessageTemplateLocation
  ) => Promise<TKakaoLinkResult>;
  commerceLink: (
    template: TKakaoMessageTemplateCommerce
  ) => Promise<TKakaoLinkResult>;
  textLink: (template: TKakaoMessageTemplateText) => Promise<TKakaoLinkResult>;
};

const { RNKakaoLink } = NativeModules;

export default RNKakaoLink as TKakaoLink;
