//
//  RNKakaoLogin.h
//  Kakao
//
//  Created by Junseong Park on 2021/06/03.
//  Copyright © 2021 Facebook. All rights reserved.
//

#ifndef RNKakaoLogin_h
#define RNKakaoLogin_h

@class RNKakaoLogin;

@interface RNKakaoLogin : NSObject
- (RNKakaoLogin *)returnSwiftClassInstance;
+ (BOOL)isKakaoTalkLoginUrl:(NSURL *)url;
+ (BOOL)handleOpenUrl:(NSURL *)url;
@end

#endif /* RNKakaoLogin_h */
