package com.kweiza.reactnativekakaosdk

import android.content.Context
import com.facebook.react.bridge.*
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User
import java.util.*

class RNKakaoLogin(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return "RNKakaoLogin"
  }

  init {
    val kakaoAppKey = reactContext.resources.getString(
      reactContext.resources.getIdentifier("kakao_app_key", "string", reactContext.packageName))
    KakaoSdk.init(reactContext, kakaoAppKey)
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
          map.putString("expiresAt", accessTokenExpiresAt?.toString())
          map.putString("refreshToken", refreshToken)
          map.putString("refreshTokenExpiresAt", refreshTokenExpiresAt?.toString())
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
        map.putString("expiresAt", accessTokenExpiresAt?.toString())
        map.putString("refreshToken", refreshToken)
        map.putString("refreshTokenExpiresAt", refreshTokenExpiresAt?.toString())
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
            map.putString("expiresAt", accessTokenExpiresAt?.toString())
            map.putString("refreshToken", refreshToken)
            map.putString("refreshTokenExpiresAt", refreshTokenExpiresAt?.toString())
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
          map.putString("expiresAt", accessTokenExpiresAt?.toString())
          map.putString("refreshToken", refreshToken)
          map.putString("refreshTokenExpiresAt", refreshTokenExpiresAt?.toString())
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
        map.putInt("appId", appId.toInt())
        map.putInt("id", id.toInt())
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
        propsMap.putString("nickname", user.properties?.get("nickname"))
        propsMap.putString("profile_image", user.properties?.get("profile_image"))
        propsMap.putString("thumbnail_image", user.properties?.get("thumbnail_image"))
        map.putMap("properties", propsMap)

        val kakaoUser = user.kakaoAccount
        val kakaoAccountMap = Arguments.createMap()
        kakaoAccountMap.putBoolean("profileNeedsAgreement", kakaoUser?.profileNeedsAgreement ?: false)
        kakaoAccountMap.putBoolean("profileNicknameNeedsAgreement", kakaoUser?.profileNicknameNeedsAgreement ?: false)
        kakaoAccountMap.putBoolean("profileImageNeedsAgreement", kakaoUser?.profileImageNeedsAgreement ?: false)

        val profileMap = Arguments.createMap()
        profileMap.putString("nickname", kakaoUser?.profile?.nickname)
        profileMap.putString("profileImageUrl", kakaoUser?.profile?.profileImageUrl)
        profileMap.putString("thumbnailImageUrl", kakaoUser?.profile?.thumbnailImageUrl)
        profileMap.putBoolean("isDefaultImage", kakaoUser?.profile?.isDefaultImage ?: false)
        kakaoAccountMap.putMap("profile", profileMap)

        kakaoAccountMap.putBoolean("emailNeedsAgreement", kakaoUser?.emailNeedsAgreement ?: false)
        kakaoAccountMap.putBoolean("isEmailValid", kakaoUser?.isEmailValid ?: false)
        kakaoAccountMap.putBoolean("isEmailVerified", kakaoUser?.isEmailVerified ?: false)
        kakaoAccountMap.putString("email", kakaoUser?.email)
        kakaoAccountMap.putBoolean("ageRangeNeedsAgreement", kakaoUser?.ageRangeNeedsAgreement ?: false)
        kakaoAccountMap.putString("ageRange", kakaoUser?.ageRange?.toString())
        kakaoAccountMap.putBoolean("birthyearNeedsAgreement", kakaoUser?.birthyearNeedsAgreement ?: false)
        kakaoAccountMap.putString("birthyear", kakaoUser?.birthyear)
        kakaoAccountMap.putBoolean("birthdayNeedsAgreement", kakaoUser?.birthdayNeedsAgreement ?: false)
        kakaoAccountMap.putString("birthday", kakaoUser?.birthday)
        kakaoAccountMap.putString("birthdayType", kakaoUser?.birthdayType?.toString())
        kakaoAccountMap.putBoolean("genderNeedsAgreement", kakaoUser?.genderNeedsAgreement ?: false)
        kakaoAccountMap.putString("gender", kakaoUser?.gender?.toString())
        kakaoAccountMap.putBoolean("phoneNumberNeedsAgreement", kakaoUser?.phoneNumberNeedsAgreement ?: false)
        kakaoAccountMap.putString("phoneNumber", kakaoUser?.phoneNumber)
        kakaoAccountMap.putBoolean("ciNeedsAgreement", kakaoUser?.ciNeedsAgreement ?: false)
        kakaoAccountMap.putString("ci", kakaoUser?.ci)
        kakaoAccountMap.putString("ciAuthenticatedAt",
          kakaoUser?.ciAuthenticatedAt?.toString()
        )
        kakaoAccountMap.putBoolean("legalNameNeedsAgreement", kakaoUser?.legalNameNeedsAgreement ?: false)
        kakaoAccountMap.putString("legalName", kakaoUser?.legalName)
        kakaoAccountMap.putBoolean("legalBirthDateNeedsAgreement", kakaoUser?.legalBirthDateNeedsAgreement ?: false)
        kakaoAccountMap.putString("legalBirthDate", kakaoUser?.legalBirthDate)
        kakaoAccountMap.putBoolean("legalGenderNeedsAgreement", kakaoUser?.legalGenderNeedsAgreement ?: false)
        kakaoAccountMap.putString("legalGender", kakaoUser?.legalGender?.toString())
        kakaoAccountMap.putBoolean("isKoreanNeedsAgreement", kakaoUser?.isKoreanNeedsAgreement ?: false)
        kakaoAccountMap.putBoolean("isKorean", kakaoUser?.isKorean ?: false)
        map.putMap("kakaoAccount", kakaoAccountMap)
        map.putString("groupUserToken", user.groupUserToken)
        map.putString("connectedAt", user.connectedAt?.toString())
        map.putString("synchedAt", user.synchedAt?.toString())
        map.putBoolean("hasSignedUp", user.hasSignedUp ?: false)

        promise.resolve(map)
        return@me
      }

      promise.reject("RNKakaoLogin me", "User is null")
    }
  }
}
