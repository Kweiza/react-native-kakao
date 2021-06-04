//
//  RNKakaoLink.swift
//  Kakao
//
//  Created by Junseong Park on 2021/06/04.
//  Copyright © 2021 Facebook. All rights reserved.
//

import Foundation

import KakaoSDKCommon
import KakaoSDKLink
import KakaoSDKTemplate

@objc(RNKakaoLink)
class RNKakaoLink: RNKakao {
    @objc(feedLink:resolver:rejecter:)
    func feedLink(_ template: NSDictionary, resolver resolve: @escaping RCTPromiseResolveBlock,
                  rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        DispatchQueue.main.async {
            if let jsonData = try? JSONSerialization.data(withJSONObject: template, options: []) {
                if let templatable = try? SdkJSONDecoder.custom.decode(FeedTemplate.self, from: jsonData) {
                    LinkApi.shared.defaultLink(templatable: templatable) {(linkResult, error) in
                        if let error = error {
                            reject("RNKakaoLink feedLink", error.localizedDescription, nil)
                        }
                        else {
                            if let linkResult = linkResult {
                                UIApplication.shared.open(linkResult.url, options: [:], completionHandler: nil)
                            }
                            resolve(linkResult)
                        }
                    }
                } else {
                    reject("RNKakaoLink feedLink", "fail to decode json", nil)
                }
            } else {
                reject("RNKakaoLink feedLink", "fail to serialize json", nil)
            }
        }
    }
    
    @objc(listLink:resolver:rejecter:)
    func listLink(_ template: NSDictionary, resolver resolve: @escaping RCTPromiseResolveBlock,
                  rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        DispatchQueue.main.async {
            if let jsonData = try? JSONSerialization.data(withJSONObject: template, options: []) {
                if let templatable = try? SdkJSONDecoder.custom.decode(ListTemplate.self, from: jsonData) {
                    LinkApi.shared.defaultLink(templatable: templatable) {(linkResult, error) in
                        if let error = error {
                            reject("RNKakaoLink listLink", error.localizedDescription, nil)
                        }
                        else {
                            if let linkResult = linkResult {
                                UIApplication.shared.open(linkResult.url, options: [:], completionHandler: nil)
                            }
                            resolve(linkResult)
                        }
                    }
                } else {
                    reject("RNKakaoLink listLink", "fail to decode json", nil)
                }
            } else {
                reject("RNKakaoLink listLink", "fail to serialize json", nil)
            }
        }
    }
    
    @objc(locationLink:resolver:rejecter:)
    func locationLink(_ template: NSDictionary, resolver resolve: @escaping RCTPromiseResolveBlock,
                  rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        DispatchQueue.main.async {
            if let jsonData = try? JSONSerialization.data(withJSONObject: template, options: []) {
                if let templatable = try? SdkJSONDecoder.custom.decode(LocationTemplate.self, from: jsonData) {
                    LinkApi.shared.defaultLink(templatable: templatable) {(linkResult, error) in
                        if let error = error {
                            reject("RNKakaoLink locationLink", error.localizedDescription, nil)
                        }
                        else {
                            if let linkResult = linkResult {
                                UIApplication.shared.open(linkResult.url, options: [:], completionHandler: nil)
                            }
                            resolve(linkResult)
                        }
                    }
                } else {
                    reject("RNKakaoLink locationLink", "fail to decode json", nil)
                }
            } else {
                reject("RNKakaoLink locationLink", "fail to serialize json", nil)
            }
        }
    }
    
    @objc(commerceLink:resolver:rejecter:)
    func commerceLink(_ template: NSDictionary, resolver resolve: @escaping RCTPromiseResolveBlock,
                  rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        DispatchQueue.main.async {
            if let jsonData = try? JSONSerialization.data(withJSONObject: template, options: []) {
                if let templatable = try? SdkJSONDecoder.custom.decode(CommerceTemplate.self, from: jsonData) {
                    LinkApi.shared.defaultLink(templatable: templatable) {(linkResult, error) in
                        if let error = error {
                            reject("RNKakaoLink commerceLink", error.localizedDescription, nil)
                        }
                        else {
                            if let linkResult = linkResult {
                                UIApplication.shared.open(linkResult.url, options: [:], completionHandler: nil)
                            }
                            resolve(linkResult)
                        }
                    }
                } else {
                    reject("RNKakaoLink commerceLink", "fail to decode json", nil)
                }
            } else {
                reject("RNKakaoLink commerceLink", "fail to serialize json", nil)
            }
        }
    }
    
    @objc(textLink:resolver:rejecter:)
    func textLink(_ template: NSDictionary, resolver resolve: @escaping RCTPromiseResolveBlock,
                  rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        DispatchQueue.main.async {
            if let jsonData = try? JSONSerialization.data(withJSONObject: template, options: []) {
                if let templatable = try? SdkJSONDecoder.custom.decode(TextTemplate.self, from: jsonData) {
                    LinkApi.shared.defaultLink(templatable: templatable) {(linkResult, error) in
                        if let error = error {
                            reject("RNKakaoLink textLink", error.localizedDescription, nil)
                        }
                        else {
                            if let linkResult = linkResult {
                                UIApplication.shared.open(linkResult.url, options: [:], completionHandler: nil)
                            }
                            resolve(linkResult)
                        }
                    }
                } else {
                    reject("RNKakaoLink textLink", "fail to decode json", nil)
                }
            } else {
                reject("RNKakaoLink textLink", "fail to serialize json", nil)
            }
        }
    }
}
