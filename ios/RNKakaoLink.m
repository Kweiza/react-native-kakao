//
//  RNKakaoLink.m
//  Kakao
//
//  Created by Junseong Park on 2021/06/04.
//  Copyright © 2021 Facebook. All rights reserved.
//

#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(RNKakaoLink, NSObject)

RCT_EXTERN_METHOD(feedLink:(NSDictionary *)template resolver:(RCTPromiseResolveBlock *)resolve rejecter:(RCTPromiseRejectBlock *)reject);
RCT_EXTERN_METHOD(listLink:(NSDictionary *)template resolver:(RCTPromiseResolveBlock *)resolve rejecter:(RCTPromiseRejectBlock *)reject);
RCT_EXTERN_METHOD(locationLink:(NSDictionary *)template resolver:(RCTPromiseResolveBlock *)resolve rejecter:(RCTPromiseRejectBlock *)reject);
RCT_EXTERN_METHOD(commerceLink:(NSDictionary *)template resolver:(RCTPromiseResolveBlock *)resolve rejecter:(RCTPromiseRejectBlock *)reject);
RCT_EXTERN_METHOD(textLink:(NSDictionary *)template resolver:(RCTPromiseResolveBlock *)resolve rejecter:(RCTPromiseRejectBlock *)reject);

@end
