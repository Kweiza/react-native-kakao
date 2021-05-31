import KakaoSDKCommon

@objc(Kakao)
class Kakao: NSObject {
    public override init() {
        let appKey: String? = Bundle.main.object(forInfoDictionaryKey: "KAKAO_APP_KEY") as? String
        KakaoSDKCommon.initSDK(appKey: appKey!)
    }

    @objc(multiply:withB:withResolver:withRejecter:)
    func multiply(a: Float, b: Float, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        resolve(a*b)
    }
}
