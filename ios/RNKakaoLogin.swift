//
//  RNKakaoLogin.swift
//  Kakao
//
//  Created by Junseong Park on 2021/06/01.
//  Copyright © 2021 Facebook. All rights reserved.
//

import Foundation

import KakaoSDKCommon
import KakaoSDKAuth
import KakaoSDKUser

@objc(RNKakaoLogin)
class RNKakaoLogin: RNKakao {
    @objc(isKakaoTalkLoginUrl:)
    public static func isKakaoTalkLoginUrl(url:URL) -> Bool {
        
        let appKey = try? KakaoSDKCommon.shared.appKey();
        
        if (appKey != nil) {
            return AuthApi.isKakaoTalkLoginUrl(url)
        }
        return false
    }
    
    @objc(handleOpenUrl:)
    public static func handleOpenUrl(url:URL) -> Bool {
        return AuthController.handleOpenUrl(url: url)
    }
    
    @objc(login:rejecter:)
    func login(_ resolve: @escaping RCTPromiseResolveBlock,
               rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        DispatchQueue.main.async {
            if (UserApi.isKakaoTalkLoginAvailable()) {
                UserApi.shared.loginWithKakaoTalk {(oauthToken, error) in
                    if let error = error {
                        reject("RNKakaoLogin login loginWithKakaoTalk", error.localizedDescription, nil)
                    }
                    else {
                        resolve([
                            "accessToken": oauthToken?.accessToken ?? "",
                            "expiredAt": oauthToken!.expiredAt,
                            "refreshToken": oauthToken?.refreshToken ?? "",
                            "refreshTokenExpiredAt": oauthToken!.refreshTokenExpiredAt,
                            "scopes": oauthToken?.scopes ?? "",
                        ])
                    }
                }
            } else {
                UserApi.shared.loginWithKakaoAccount {(oauthToken, error) in
                    if let error = error {
                        reject("RNKakaoLogin login loginWithKakaoAccount", error.localizedDescription, nil)
                    }
                    else {
                        resolve([
                            "accessToken": oauthToken?.accessToken ?? "",
                            "refreshToken": oauthToken?.refreshToken ?? "",
                            "accessTokenExpiresAt": oauthToken!.expiredAt,
                            "refreshTokenExpiresAt": oauthToken!.refreshTokenExpiredAt,
                            "scopes": oauthToken?.scopes ?? "",
                        ]);
                    }
                }
            }
        }
    }
    
    @objc(logout:rejecter:)
    func logout(_ resolve: @escaping RCTPromiseResolveBlock,
                rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        DispatchQueue.main.async {
            UserApi.shared.logout {(error) in
                if let error = error {
                    reject("RNKakaoLogin logout", error.localizedDescription, nil)
                }
                else {
                    resolve("Successfully logged out")
                }
            }
        }
    }
    
    @objc(unlink:rejecter:)
    func unlink(_ resolve: @escaping RCTPromiseResolveBlock,
                rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        DispatchQueue.main.async {
            UserApi.shared.unlink {(error) in
                if let error = error {
                    reject("RNKakaoLogin unlink", error.localizedDescription, nil)
                }
                else {
                    resolve("Successfully unlinked")
                }
            }
        }
    }
    
    @objc(getAccessToken:rejecter:)
    func accessTokenInfo(_ resolve: @escaping RCTPromiseResolveBlock,
                        rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        DispatchQueue.main.async {
            UserApi.shared.accessTokenInfo {(accessTokenInfo, error) in
                if let error = error {
                    reject("RNKakaoLogin accessToken", error.localizedDescription, nil)
                }
                else {
                    resolve([
                        "appId": accessTokenInfo.appId,
                        "id": accessTokenInfo?.id,
                        "expiresIn": accessTokenInfo?.expiresIn,
                    ])
                }
            }
        }
    }
    
    @objc(getProfile:rejecter:)
    func me(_ resolve: @escaping RCTPromiseResolveBlock,
                    rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        DispatchQueue.main.async {
            UserApi.shared.me() {(user, error) in
                if let error = error {
                    reject("RNKakaoLogin me", error.localizedDescription, nil)
                }
                else {
                    resolve([
                        "id": user.id,
                        "properties": [
                            "nickname": user.properties?.nickname,
                            "profile_image": user.properties?.profile_image,
                            "thumbnail_image": user.properties?.thumbnail_image,
                        ],
                        "kakaoAccount": [
                            "profileNeedsAgreement": user.kakaoAccount?.profileNeedsAgreement,
                            "profileNicknameNeedsAgreement": user.kakaoAccount?.profileNicknameNeedsAgreement,
                            "profileImageNeedsAgreement": user.kakaoAccount?.profileImageNeedsAgreement,
                            "profile": [
                                "nickname": user.kakaoAccount?.profile?.nickname,
                                "profileImageUrl": user.kakaoAccount?.profile?.profileImageUrl,
                                "thumbnailImageUrl": user.kakaoAccount?.profile?.thumbnailImageUrl,
                                "isDefaultImage": user.kakaoAccount?.profile?.isDefaultImage,
                            ],
                            "emailNeedsAgreement": user.kakaoAccount?.emailNeedsAgreement,
                            "isEmailValid": user.kakaoAccount?.isEmailValid,
                            "isEmailVerified": user.kakaoAccount?.isEmailVerified,
                            "email": user.kakaoAccount?.email,
                            "ageRangeNeedsAgreement": user.kakaoAccount?.ageRangeNeedsAgreement,
                            "ageRange": user.kakaoAccount?.ageRange,
                            "birthyearNeedsAgreement": user.kakaoAccount?.birthyearNeedsAgreement,
                            "birthyear": user.kakaoAccount?.birthyear,
                            "birthdayNeedsAgreement": user.kakaoAccount?.birthdayNeedsAgreement,
                            "birthday": user.kakaoAccount?.birthday,
                            "birthdayType": user.kakaoAccount?.birthdayType,
                            "genderNeedsAgreement": userkakaoAccount?.genderNeedsAgreement,
                            "gender": user.kakaoAccount?.gender,
                            "phoneNumberNeedsAgreement": user.kakaoAccount?.phoneNumberNeedsAgreement,
                            "phoneNumber": user.kakaoAccount?.phoneNumber,
                            "ciNeedsAgreement": user.kakaoAccount?.ciNeedsAgreement,
                            "ci": user.kakaoAccount?.ci,
                            "ciAuthenticatedAt": user.kakaoAccount?.ciAuthenticatedAt,
                            "legalNameNeedsAgreement": user.kakaoAccount?.legalNameNeedsAgreement,
                            "legalName": user.kakaoAccount?.legalName,
                            "legalBirthDateNeedsAgreement": user.kakaoAccount?.legalBirthDateNeedsAgreement,
                            "legalBirthDate": user.kakaoAccount?.legalBirthDate,
                            "legalGenderNeedsAgreement": userkakaoAccount?.legalGenderNeedsAgreement,
                            "legalGender": user.kakaoAccount?.legalGender,
                            "isKoreanNeedsAgreement": user.kakaoAccount?.isKoreanNeedsAgreement,
                            "isKorean": user.kakaoAccount?.isKorean,
                        ],
                        "groupUserToken": user.groupUserToken,
                        "connectedAt": user.connectedAt,
                        "synchedAt": user.synchedAt,
                        "hasSignedUp": user.hasSignedUp,
                    ])
                }
            }
        }
    }
    
}
