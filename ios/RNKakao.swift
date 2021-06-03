import KakaoSDKCommon

@objc(RNKakao)
class RNKakao: NSObject {
    public override init() {
        let appKey: String? = Bundle.main.object(forInfoDictionaryKey: "KAKAO_APP_KEY") as? String
        KakaoSDKCommon.initSDK(appKey: appKey!)
    }
}
