import RNKakaoLogin, { TRNKakaoLogin } from './KakaoLogin';
export { TKakaoOAuthToken, TRNKakaoLogin } from './KakaoLogin';

export const KakaoLogin = RNKakaoLogin as TRNKakaoLogin;

export default {
  KakaoLogin,
};
