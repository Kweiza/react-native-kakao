package com.kweiza.reactnativekakao

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule

import com.kakao.sdk.common.KakaoSdk

class RNKakao(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "RNKakao"
    }

    init {
        val kakaoAppKey = reactContext.resources.getString(
                reactContext.resources.getIdentifier("kakao_app_key", "string", reactContext.packageName))
        KakaoSdk.init(reactContext, kakaoAppKey)
    }
}
