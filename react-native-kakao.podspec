require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "react-native-kakao"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.homepage     = package["homepage"]
  s.license      = package["license"]
  s.authors      = package["author"]

  s.platforms    = { :ios => "11.0" }
  s.source       = { :git => "https://github.com/Kweiza/react-native-kakao.git", :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,m,mm,swift}"

  s.dependency "React-Core"

  s.dependency 'KakaoSDK'
  s.dependency 'KakaoSDKCommon'  # 필수 요소를 담은 공통 모듈
  s.dependency 'KakaoSDKAuth'  # 사용자 인증
  s.dependency 'KakaoSDKUser'  # 카카오 로그인, 사용자 관리
  s.dependency 'KakaoSDKTalk'  # 친구, 메시지(카카오톡)
  s.dependency 'KakaoSDKStory'  # 카카오스토리 
  s.dependency 'KakaoSDKLink'  # 메시지(카카오링크)
  s.dependency 'KakaoSDKTemplate'  # 메시지 템플릿 
  s.dependency 'KakaoSDKNavi'  # 카카오내비 
end
