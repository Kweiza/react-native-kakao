import { NativeModules } from 'react-native';

type KakaoType = {
  multiply(a: number, b: number): Promise<number>;
};

const { Kakao } = NativeModules;

export default Kakao as KakaoType;
