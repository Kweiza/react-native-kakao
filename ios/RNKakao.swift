import KakaoSDKCommon

@objc(RNKakao)
class RNKakao: NSObject {
    @objc
    static func requiresMainQueueSetup() -> Bool {
        return true
    }
    
    public override init() {
        let appKey: String? = Bundle.main.object(forInfoDictionaryKey: "KAKAO_APP_KEY") as? String
        KakaoSDKCommon.initSDK(appKey: appKey!)
    }
}
