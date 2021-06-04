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
    
    @objc(isKakaoTalkLoginAvailable:rejecter:)
    func isKakaoTalkLoginAvailable(_ resolve: @escaping RCTPromiseResolveBlock,
                                   rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        DispatchQueue.main.async {
            resolve(UserApi.isKakaoTalkLoginAvailable());
        }
    }
    
    @objc(loginWithKakaoTalk:rejecter:)
    func loginWithKakaoTalk(_ resolve: @escaping RCTPromiseResolveBlock,
                                   rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        DispatchQueue.main.async {
            UserApi.shared.loginWithKakaoTalk {(oauthToken, error) in
                if let error = error {
                    reject("RNKakaoLogin loginWithKakaoTalk", error.localizedDescription, nil)
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
        }
    }
    
    @objc(loginWithKakaoAccount:rejecter:)
    func loginWithKakaoAccount(_ resolve: @escaping RCTPromiseResolveBlock,
                                   rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        DispatchQueue.main.async {
            UserApi.shared.loginWithKakaoAccount {(oauthToken, error) in
                if let error = error {
                    reject("RNKakaoLogin loginWithKakaoAccount", error.localizedDescription, nil)
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
    
    @objc(accessTokenInfo:rejecter:)
    func accessTokenInfo(_ resolve: @escaping RCTPromiseResolveBlock,
                        rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        DispatchQueue.main.async {
            UserApi.shared.accessTokenInfo {(accessTokenInfo, error) in
                if let error = error {
                    reject("RNKakaoLogin accessToken", error.localizedDescription, nil)
                }
                else {
                    resolve([
                        "appId": accessTokenInfo?.appId,
                        "id": accessTokenInfo?.id,
                        "expiresIn": accessTokenInfo?.expiresIn,
                    ])
                }
            }
        }
    }
    
    @objc(me:rejecter:)
    func me(_ resolve: @escaping RCTPromiseResolveBlock,
                    rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        DispatchQueue.main.async {
            UserApi.shared.me() {(user, error) in
                if let error = error {
                    reject("RNKakaoLogin me", error.localizedDescription, nil)
                }
                else {
                    resolve([
                        "id": user?.id as Any,
                        "properties": [
                            "nickname": user?.properties?["nickname"] as Any,
                            "profile_image": user?.properties?["profile_image"] as Any,
                            "thumbnail_image": user?.properties?["thumbnail_image"] as Any,
                        ],
                        "kakaoAccount": [
                            "profileNeedsAgreement": user?.kakaoAccount?.profileNeedsAgreement as Any,
                            "profileNicknameNeedsAgreement": user?.kakaoAccount?.profileNicknameNeedsAgreement as Any,
                            "profileImageNeedsAgreement": user?.kakaoAccount?.profileImageNeedsAgreement as Any,
                            "profile": [
                                "nickname": user?.kakaoAccount?.profile?.nickname as Any,
                                "profileImageUrl": user?.kakaoAccount?.profile?.profileImageUrl as Any,
                                "thumbnailImageUrl": user?.kakaoAccount?.profile?.thumbnailImageUrl as Any,
                                "isDefaultImage": user?.kakaoAccount?.profile?.isDefaultImage as Any
                            ],
                            "emailNeedsAgreement": user?.kakaoAccount?.emailNeedsAgreement as Any,
                            "isEmailValid": user?.kakaoAccount?.isEmailValid as Any,
                            "isEmailVerified": user?.kakaoAccount?.isEmailVerified as Any,
                            "email": user?.kakaoAccount?.email as Any,
                            "ageRangeNeedsAgreement": user?.kakaoAccount?.ageRangeNeedsAgreement as Any,
                            "ageRange": user?.kakaoAccount?.ageRange as Any,
                            "birthyearNeedsAgreement": user?.kakaoAccount?.birthyearNeedsAgreement as Any,
                            "birthyear": user?.kakaoAccount?.birthyear as Any,
                            "birthdayNeedsAgreement": user?.kakaoAccount?.birthdayNeedsAgreement as Any,
                            "birthday": user?.kakaoAccount?.birthday as Any,
                            "birthdayType": user?.kakaoAccount?.birthdayType as Any,
                            "genderNeedsAgreement": user?.kakaoAccount?.genderNeedsAgreement as Any,
                            "gender": user?.kakaoAccount?.gender as Any,
                            "phoneNumberNeedsAgreement": user?.kakaoAccount?.phoneNumberNeedsAgreement as Any,
                            "phoneNumber": user?.kakaoAccount?.phoneNumber as Any,
                            "ciNeedsAgreement": user?.kakaoAccount?.ciNeedsAgreement as Any,
                            "ci": user?.kakaoAccount?.ci as Any,
                            "ciAuthenticatedAt": user?.kakaoAccount?.ciAuthenticatedAt as Any,
                            "legalNameNeedsAgreement": user?.kakaoAccount?.legalNameNeedsAgreement as Any,
                            "legalName": user?.kakaoAccount?.legalName as Any,
                            "legalBirthDateNeedsAgreement": user?.kakaoAccount?.legalBirthDateNeedsAgreement as Any,
                            "legalBirthDate": user?.kakaoAccount?.legalBirthDate as Any,
                            "legalGenderNeedsAgreement": user?.kakaoAccount?.legalGenderNeedsAgreement as Any,
                            "legalGender": user?.kakaoAccount?.legalGender as Any,
                            "isKoreanNeedsAgreement": user?.kakaoAccount?.isKoreanNeedsAgreement as Any,
                            "isKorean": user?.kakaoAccount?.isKorean as Any,
                        ],
                        "groupUserToken": user?.groupUserToken as Any,
                        "connectedAt": user?.connectedAt as Any,
                        "synchedAt": user?.synchedAt as Any,
                        "hasSignedUp": user?.hasSignedUp as Any,
                    ])
                }
            }
        }
    }
    
}
