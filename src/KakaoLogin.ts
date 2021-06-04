import { NativeModules } from 'react-native';

export type TKakaoOAuthToken = {
  accessToken: string;
  expiredAt: Date;
  refreshToken: string;
  refreshTokenExpiresAt: Date;
  scopes: string[];
};

export type TKakaoAccessTokenInfo = {
  appId: number;
  id?: number;
  expiresIn: number;
};

export type TKakaoUserProperties = {
  nickname?: string;
  profile_image?: string;
  thumbnail_image?: string;
};
export type TKakaoUserProfile = {
  nickname?: string;
  profileImageUrl?: string;
  thumbnailImageUrl?: string;
  isDefaultImage?: boolean;
};
export type TKakaoUserAgeRange = {
  Age0_9: '0~9';
  Age10_14: '10~19';
  Age15_19: '15~19';
  Age20_29: '20~29';
  Age30_39: '30~39';
  Age40_49: '40~49';
  Age50_59: '50~59';
  Age60_69: '60~69';
  Age70_79: '70~79';
  Age80_89: '80~89';
  Age90_Above: '90~';
};
export type TKakaoUserBirthdayType = {
  Solar: 'SOLAR';
  Lunar: 'LUNAR';
};
export type TKakaoUserGender = {
  Male: 'male';
  Female: 'female';
};
export type TKakaoUserKakaoAccount = {
  profileNeedsAgreement?: boolean;
  profileNicknameNeedsAgreement?: boolean;
  profileImageNeedsAgreement?: boolean;
  profile?: TKakaoUserProfile;
  emailNeedsAgreement?: boolean;
  isEmailValid?: boolean;
  isEmailVerified?: boolean;
  email?: string;
  ageRangeNeedsAgreement?: boolean;
  ageRange?: TKakaoUserAgeRange;
  birthyearNeedsAgreement?: boolean;
  birthyear?: string;
  birthdayNeedsAgreement?: boolean;
  birthday?: string;
  birthdayType?: TKakaoUserBirthdayType;
  genderNeedsAgreement?: boolean;
  gender?: TKakaoUserGender;
  phoneNumberNeedsAgreement?: boolean;
  phoneNumber?: string;
  ciNeedsAgreement?: boolean;
  ci?: string;
  ciAuthenticatedAt?: Date;
  legalNameNeedsAgreement?: boolean;
  legalName?: string;
  legalBirthDateNeedsAgreement?: boolean;
  legalBirthDate?: string;
  legalGenderNeedsAgreement?: boolean;
  legalGender?: TKakaoUserGender;
  isKoreanNeedsAgreement?: boolean;
  isKorean?: boolean;
};
export type TKakaoUser = {
  id?: number;
  properties?: TKakaoUserProperties;
  kakaoAccount?: TKakaoUserKakaoAccount;
  groupUserToken?: string;
  connectedAt?: Date;
  synchedAt?: Date;
  hasSignedUp?: boolean;
};

export type TRNKakaoLogin = {
  isKakaoTalkLoginAvailable(): Promise<boolean>;
  loginWithKakaoTalk(): Promise<TKakaoOAuthToken>;
  loginWithKakaoAccount(): Promise<TKakaoOAuthToken>;
  login(): Promise<TKakaoOAuthToken>;
  logout(): Promise<void>;
  unlink(): Promise<void>;
  accessTokenInfo(): Promise<TKakaoAccessTokenInfo>;
  me(): Promise<void>;
};

const { RNKakaoLogin } = NativeModules;

export default RNKakaoLogin as TRNKakaoLogin;
