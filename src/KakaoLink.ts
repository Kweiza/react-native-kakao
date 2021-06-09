import { NativeModules, Platform } from 'react-native';
import _ from 'lodash';

import type {
  TKakaoMessageLink,
  TKakaoMessageTemplateCommerce,
  TKakaoMessageTemplateFeed,
  TKakaoMessageTemplateList,
  TKakaoMessageTemplateLocation,
  TKakaoMessageTemplateText,
} from './KakaoMessageTemplate';

export type TKakaoLinkResult = {
  argumentMsg: {[key: string]: string};
  warningMsg: {[key: string]: string};
};

export type TKakaoLink = {
  feedLink: (template: TKakaoMessageTemplateFeed) => Promise<TKakaoLinkResult>;
  listLink: (template: TKakaoMessageTemplateList) => Promise<TKakaoLinkResult>;
  locationLink: (
    template: TKakaoMessageTemplateLocation
  ) => Promise<TKakaoLinkResult>;
  commerceLink: (
    template: TKakaoMessageTemplateCommerce
  ) => Promise<TKakaoLinkResult>;
  textLink: (template: TKakaoMessageTemplateText) => Promise<TKakaoLinkResult>;
};

const { RNKakaoLink } = NativeModules;

const convertLinkParams = (link: TKakaoMessageLink) => {
  let ios_params: string[] = [];
  _.forEach(link.ios_execution_params as object, (v, k) => {
    ios_params.push(`${k}=${v}`);
  });

  let android_params: string[] = [];
  _.forEach(link.android_execution_params as object, (v, k) => {
    android_params.push(`${k}=${v}`);
  });

  return {
    ...link,
    ios_execution_params: _.join(ios_params, '&'),
    android_execution_params: _.join(android_params, '&'),
  };
};

export default {
  async feedLink(template: TKakaoMessageTemplateFeed) {
    let modTemplate = template;
    modTemplate.object_type = 'feed';
    if (Platform.OS === 'ios') {
      modTemplate.content.link = convertLinkParams(template.content.link);
      modTemplate.buttons = template.buttons?.map((v) => ({
        ...v,
        link: convertLinkParams(v.link),
      }));
    }
    return await RNKakaoLink.feedLink(template);
  },
  async listLink(template: TKakaoMessageTemplateList) {
    let modTemplate = template;
    modTemplate.object_type = 'list';
    if (Platform.OS === 'ios') {
      modTemplate.header_link = convertLinkParams(template.header_link);
      modTemplate.contents = template.contents.map((v) => ({
        ...v,
        link: convertLinkParams(v.link),
      }));
      modTemplate.buttons = template.buttons?.map((v) => ({
        ...v,
        link: convertLinkParams(v.link),
      }));
    }
    return await RNKakaoLink.listLink(template);
  },
  async locationLink(template: TKakaoMessageTemplateLocation) {
    let modTemplate = template;
    modTemplate.object_type = 'location';
    if (Platform.OS === 'ios') {
      modTemplate.content.link = convertLinkParams(template.content.link);
      modTemplate.buttons = template.buttons?.map((v) => ({
        ...v,
        link: convertLinkParams(v.link),
      }));
    }
    return await RNKakaoLink.locationLink(template);
  },
  async commerceLink(template: TKakaoMessageTemplateCommerce) {
    let modTemplate = template;
    modTemplate.object_type = 'commerce';
    if (Platform.OS === 'ios') {
      modTemplate.content.link = convertLinkParams(template.content.link);
      modTemplate.buttons = template.buttons?.map((v) => ({
        ...v,
        link: convertLinkParams(v.link),
      }));
    }
    return await RNKakaoLink.commerceLink(template);
  },
  async textLink(template: TKakaoMessageTemplateText) {
    let modTemplate = template;
    modTemplate.object_type = 'text';
    if (Platform.OS === 'ios') {
      modTemplate.link = convertLinkParams(template.link);
      modTemplate.buttons = template.buttons?.map((v) => ({
        ...v,
        link: convertLinkParams(v.link),
      }));
    }
    return await RNKakaoLink.textLink(template);
  },
} as TKakaoLink;
