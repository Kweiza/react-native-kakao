package com.kweiza.reactnativekakaosdk

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.Arguments

import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User

class KakaoLoginModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "KakaoLogin"
    }

    @ReactMethod
    private fun isKakaoTalkLoginAvailable(promise: Promise) {
        promise.resolve(UserApiClient.instance.isKakaoTalkLoginAvailable(reactContext));
        return@isKakaoTalkLoginAvailable
    }

    @ReactMethod
    private fun loginWithKakaoTalk(promise: Promise) {
        reactContext.currentActivity?.let {
            UserApiClient.instance.loginWithKakaoTalk(it) { token, error: Throwable? ->
                if (error != null) {
                    promise.reject("RNKakaoLogin loginWithKakaoTalk", error.message, error)

                    return@loginWithKakaoTalk
                }

                if (token != null) {
                    val (accessToken, accessTokenExpiresAt, refreshToken, refreshTokenExpiresAt, scopes) = token
                    val map = Arguments.createMap()
                    map.putString("accessToken", accessToken)
                    map.putString("expiresAt", accessTokenExpiresAt)
                    map.putString("refreshToken", refreshToken)
                    map.putString("refreshTokenExpiresAt", refreshTokenExpiresAt)
                    val scopeArray = Arguments.createArray()
                    if (scopes != null) {
                        for (scope in scopes) {
                            scopeArray.pushString(scope)
                        }
                    }
                    map.putArray("scopes", scopeArray)
                    promise.resolve(map)
                    return@loginWithKakaoTalk
                }

                promise.reject("RNKakaoLogin loginWithKakaoTalk", "Token is null")
            }
        }
    }

    @ReactMethod
    private fun loginWithKakaoAccount(promise: Promise) {
        UserApiClient.instance.loginWithKakaoAccount(reactContext) { token, error: Throwable? ->
            if (error != null) {
                promise.reject("RNKakaoLogin loginWithKakaoAccount", error.message, error)
                return@loginWithKakaoAccount
            }

            if (token != null) {
                val (accessToken, accessTokenExpiresAt, refreshToken, refreshTokenExpiresAt, scopes) = token
                val map = Arguments.createMap()
                map.putString("accessToken", accessToken)
                map.putString("expiresAt", accessTokenExpiresAt)
                map.putString("refreshToken", refreshToken)
                map.putString("refreshTokenExpiresAt", refreshTokenExpiresAt)
                val scopeArray = Arguments.createArray()
                if (scopes != null) {
                    for (scope in scopes) {
                        scopeArray.pushString(scope)
                    }
                }
                map.putArray("scopes", scopeArray)
                promise.resolve(map)
                return@loginWithKakaoAccount
            }

            promise.reject("RNKakaoLogin loginWithKakaoAccount", "Token is null")
        }
    }

    @ReactMethod
    private fun login(promise: Promise) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(reactContext)) {
            reactContext.currentActivity?.let {
                UserApiClient.instance.loginWithKakaoTalk(it) { token, error: Throwable? ->
                    if (error != null) {
                        promise.reject("RNKakaoLogin login", error.message, error)

                        return@loginWithKakaoTalk
                    }

                    if (token != null) {
                        val (accessToken, accessTokenExpiresAt, refreshToken, refreshTokenExpiresAt, scopes) = token
                        val map = Arguments.createMap()
                        map.putString("accessToken", accessToken)
                        map.putString("expiresAt", accessTokenExpiresAt)
                        map.putString("refreshToken", refreshToken)
                        map.putString("refreshTokenExpiresAt", refreshTokenExpiresAt)
                        val scopeArray = Arguments.createArray()
                        if (scopes != null) {
                            for (scope in scopes) {
                                scopeArray.pushString(scope)
                            }
                        }
                        map.putArray("scopes", scopeArray)
                        promise.resolve(map)
                        return@loginWithKakaoTalk
                    }

                    promise.reject("RNKakaoLogin login", "Token is null")
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(reactContext) { token, error: Throwable? ->
                if (error != null) {
                    promise.reject("RNKakaoLogin login", error.message, error)
                    return@loginWithKakaoAccount
                }

                if (token != null) {
                    val (accessToken, accessTokenExpiresAt, refreshToken, refreshTokenExpiresAt, scopes) = token
                    val map = Arguments.createMap()
                    map.putString("accessToken", accessToken)
                    map.putString("expiresAt", accessTokenExpiresAt)
                    map.putString("refreshToken", refreshToken)
                    map.putString("refreshTokenExpiresAt", refreshTokenExpiresAt)
                    val scopeArray = Arguments.createArray()
                    if (scopes != null) {
                        for (scope in scopes) {
                            scopeArray.pushString(scope)
                        }
                    }
                    map.putArray("scopes", scopeArray)
                    promise.resolve(map)
                    return@loginWithKakaoAccount
                }

                promise.reject("RNKakaoLogin login", "Token is null")
            }
        }
    }

    @ReactMethod
    private fun logout(promise: Promise) {
        UserApiClient.instance.logout { error: Throwable? ->
            if (error != null) {
                promise.reject("RNKakaoLogin logout", error.message, error)
                return@logout
            }
            promise.resolve("Successfully logged out")
            null
        }
    }

    @ReactMethod
    private fun unlink(promise: Promise) {
        UserApiClient.instance.unlink { error: Throwable? ->
            if (error != null) {
                promise.reject("RNKakaoLogin unlink", error.message, error)
                return@unlink
            }
            promise.resolve("Successfully unlinked")
            null
        }
    }

    @ReactMethod
    private fun accessTokenInfo(promise: Promise) {
        UserApiClient.instance.accessTokenInfo { token, error: Throwable? ->
            if (error != null) {
                promise.reject("RNKakaoLogin accessTokenInfo", error.message, error)
                return@accessTokenInfo
            }

            if (token != null) {
                val (appId, id, expiresIn) = token
                val map = Arguments.createMap()
                map.putInt("appId", appId)
                map.putInt("id", id)
                map.putInt("expiresIn", expiresIn)
                promise.resolve(map)
                return@accessTokenInfo
            }

            promise.reject("RNKakaoLogin accessTokenInfo", "Token is null")
        }
    }

    private fun convertValue(`val`: Boolean?): Boolean {
        return `val` ?: false
    }

    @ReactMethod
    private fun me(promise: Promise) {
        UserApiClient.instance.me { user: User?, error: Throwable? ->
            if (error != null) {
                promise.reject("RNKakaoLogin me", error.message, error)
                return@me
            }

            if (user != null) {
                val map = Arguments.createMap()
                map.putString("id", user.id.toString())

                val propsMap = Arguments.createMap()
                propsMap.putString("nickname", user.properties.get("nickname"))
                propsMap.putString("profile_image", user.properties.get("profile_image"))
                propsMap.putString("thumbnail_image", user.properties.get("thumbnail_image"))
                map.putMap("properties", propsMap)
                
                val kakaoAccountMap = Arguments.createMap()
                val kakaoUser = user.kakaoAccount
                map.putString("email", kakaoUser!!.email.toString())
                map.putString("nickname", kakaoUser.profile?.nickname)
                map.putString("profileImageUrl", kakaoUser.profile?.profileImageUrl)
                map.putString("thumbnailImageUrl", kakaoUser.profile?.thumbnailImageUrl)

                map.putString("phoneNumber", kakaoUser.phoneNumber.toString())
                map.putString("ageRange", user.kakaoAccount!!.ageRange.toString())
                map.putString("birthday", kakaoUser.birthday.toString())
                map.putString("birthdayType", kakaoUser.birthdayType.toString())
                map.putString("birthyear", kakaoUser.birthyear.toString())
                map.putString("gender", kakaoUser.gender.toString())
                map.putBoolean("isEmailValid", convertValue(kakaoUser.isEmailValid))
                map.putBoolean("isEmailVerified", convertValue(kakaoUser.isEmailVerified))
                map.putBoolean("isKorean", convertValue(kakaoUser.isKorean))
                map.putBoolean("ageRangeNeedsAgreement", convertValue(kakaoUser.ageRangeNeedsAgreement))
                map.putBoolean("birthdayNeedsAgreement", convertValue(kakaoUser.birthdayNeedsAgreement))
                map.putBoolean("birthyearNeedsAgreement", convertValue(kakaoUser.birthyearNeedsAgreement))
                map.putBoolean("emailNeedsAgreement", convertValue(kakaoUser.emailNeedsAgreement))
                map.putBoolean("genderNeedsAgreement", convertValue(kakaoUser.genderNeedsAgreement))
                map.putBoolean("isKoreanNeedsAgreement", convertValue(kakaoUser.isKoreanNeedsAgreement))
                map.putBoolean("phoneNumberNeedsAgreement", convertValue(kakaoUser.phoneNumberNeedsAgreement))
                map.putBoolean("profileNeedsAgreement", convertValue(kakaoUser.profileNeedsAgreement))
                promise.resolve(map)
                return@me
            }

            promise.reject("RNKakaoLogin me", "User is null")
        }
    }
}
