import RNKakaoLogin, { TKakaoLogin } from './KakaoLogin';
export { TKakaoOAuthToken, TKakaoLogin } from './KakaoLogin';
export const KakaoLogin = RNKakaoLogin as TKakaoLogin;

import RNKakaoLink, { TKakaoLink } from './KakaoLink';
export { TKakaoLink } from './KakaoLink';
export const KakaoLink = RNKakaoLink as TKakaoLink;

export default {
  KakaoLogin,
  KakaoLink,
};
