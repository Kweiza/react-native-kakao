package com.kweiza.reactnativekakaosdk

import androidx.core.content.ContextCompat.startActivity
import com.facebook.react.bridge.*
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.link.LinkClient
import com.kakao.sdk.link.model.LinkResult
import com.kakao.sdk.template.model.*
import java.util.*
import kotlin.collections.ArrayList


class RNKakaoLink(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return "RNKakaoLink"
  }

  init {
    val kakaoAppKey = reactContext.resources.getString(
      reactContext.resources.getIdentifier("kakao_app_key", "string", reactContext.packageName))
    KakaoSdk.init(reactContext, kakaoAppKey)
  }

  private fun linkWithReadableMap(map: ReadableMap?): Link? {
    if (map != null) {
      return Link(
        webUrl = map.getString("web_url"),
        mobileWebUrl = map.getString("mobile_web_url"),
        androidExecutionParams = Conversion.toMap(map.getMap("android_execution_params")) as Map<String, String>?,
        iosExecutionParams = Conversion.toMap(map.getMap("ios_execution_params")) as Map<String, String>?
      )
    }
    return null
  }

  private fun contentWithReadableMap(map: ReadableMap?): Content? {
    if (map != null) {
      return Content(
        title = map.getString("title")!!,
        description = map.getString("description"),
        imageUrl = map.getString("image_url")!!,
        imageWidth = if (map.hasKey("image_width") && !map.isNull("image_width")) {
          map.getInt("image_width")
        } else {
          null
        },
        imageHeight = if (map.hasKey("image_height") && !map.isNull("image_height")) {
          map.getInt("image_height")
        } else {
          null
        },
        link = linkWithReadableMap(map.getMap("link"))!!
      )
    }
    return null
  }

  private fun contentsWithReadableArray(array: ReadableArray?): List<Content>? {
    if(array != null) {
      val list = ArrayList<Content>()
      var i = 0
      while(i < array.size()) {
        list.add(contentWithReadableMap(array.getMap(i))!!)
        i++
      }
      return list
    }
    return null
  }

  private fun socialWithReadableMap(map: ReadableMap?): Social? {
    if (map != null) {
      return Social(
        likeCount = if (map.hasKey("like_count") && !map.isNull("like_count") ) {
          map.getInt("like_count")
        } else {
          null
        },
        commentCount = if (map.hasKey("comment_count") && !map.isNull("comment_count") ) {
          map.getInt("comment_count")
        } else {
          null
        },
        sharedCount = if (map.hasKey("shared_count") && !map.isNull("shared_count") ) {
          map.getInt("shared_count")
        } else {
          null
        },
        viewCount = if (map.hasKey("view_count") && !map.isNull("view_count") ) {
          map.getInt("view_count")
        } else {
          null
        },
        subscriberCount = if (map.hasKey("subscriber_count") && !map.isNull("subscriber_count") ) {
          map?.getInt("subscriber_count")
        } else {
          null
        }
      )
    }
    return null
  }

  private fun buttonWithReadableMap(map: ReadableMap?): Button? {
    if(map != null) {
      return Button(
        title = map.getString("title")!!,
        link = linkWithReadableMap(map.getMap("link"))!!
      )
    }
    return null
  }

  private fun buttonsWithReadableArray(array: ReadableArray?): List<Button>? {
    if(array != null) {
      val list = ArrayList<Button>()
      var i = 0
      while(i < array.size()) {
        list.add(buttonWithReadableMap(array.getMap(i))!!)
        i++
      }
      return list
    }
    return null
  }

  private fun commerceWithReadableMap(map: ReadableMap?): Commerce? {
    if(map != null) {
      return Commerce(
        productName = map.getString("product_name"),
        regularPrice = map.getInt("regular_price"),
        discountPrice = if (map.hasKey("discount_price") && !map.isNull("discount_price") ) {
          map.getInt("discount_price")
        } else {
          null
        },
        discountRate = if (map.hasKey("discount_rate") && !map.isNull("discount_rate") ) {
          map.getInt("discount_rate")
        } else {
          null
        },
        fixedDiscountPrice = if (map.hasKey("fixed_discount_price") && !map.isNull("fixed_discount_price") ) {
          map.getInt("fixed_discount_price")
        } else {
          null
        }
      )
    }
    return null
  }

  private fun toWritableMap(map: Map<String, String>): WritableMap {
    val result = Arguments.createMap()
    map.forEach {
      result.putString(it.key, it.value)
    }
    return result
  }

  private fun toResultMap(linkResult: LinkResult): WritableMap {
    val result = Arguments.createMap()
    result.putMap("argumentMsg", toWritableMap(linkResult.argumentMsg))
    result.putMap("warningMsg", toWritableMap(linkResult.warningMsg))
    return result
  }

  @ReactMethod
  private fun feedLink(template: ReadableMap, promise: Promise) {
    val defaultFeed = FeedTemplate(
      content = contentWithReadableMap(template.getMap("content"))!!,
      social = socialWithReadableMap(template.getMap("social")),
      buttonTitle = template.getString("button_title"),
      buttons = buttonsWithReadableArray(template.getArray("buttons"))
    )
    LinkClient.instance.defaultTemplate(reactContext, defaultFeed) { linkResult, error ->
      if (error != null) {
        promise.reject("RNKakaoLink feedLink", error)
      }
      else if (linkResult != null) {
        startActivity(reactContext, linkResult.intent, null)
        promise.resolve(toResultMap(linkResult))
      }
    }
    return@feedLink
  }

  @ReactMethod
  private fun listLink(template: ReadableMap, promise: Promise) {
    val defaultFeed = ListTemplate(
      headerTitle = template.getString("header_title")!!,
      headerLink = linkWithReadableMap(template.getMap("header_link"))!!,
      contents = contentsWithReadableArray(template.getArray("contents"))!!,
      buttonTitle = template.getString("button_title"),
      buttons = buttonsWithReadableArray(template.getArray("buttons"))
    )
    LinkClient.instance.defaultTemplate(reactContext, defaultFeed) { linkResult, error ->
      if (error != null) {
        promise.reject("RNKakaoLink listLink", error)
      }
      else if (linkResult != null) {
        startActivity(reactContext, linkResult.intent, null)
        promise.resolve(toResultMap(linkResult))
      }
    }
    return@listLink
  }

  @ReactMethod
  private fun locationLink(template: ReadableMap, promise: Promise) {
    val defaultFeed = LocationTemplate(
      address = template.getString("address")!!,
      addressTitle = template.getString("address_title"),
      content = contentWithReadableMap(template.getMap("content"))!!,
      social = socialWithReadableMap(template.getMap("social")),
      buttonTitle = template.getString("button_title"),
      buttons = buttonsWithReadableArray(template.getArray("buttons"))
    )
    LinkClient.instance.defaultTemplate(reactContext, defaultFeed) { linkResult, error ->
      if (error != null) {
        promise.reject("RNKakaoLink locationLink", error)
      }
      else if (linkResult != null) {
        startActivity(reactContext, linkResult.intent, null)
        promise.resolve(toResultMap(linkResult))
      }
    }
    return@locationLink
  }

  @ReactMethod
  private fun commerceLink(template: ReadableMap, promise: Promise) {
    val defaultFeed = CommerceTemplate(
      content = contentWithReadableMap(template.getMap("content"))!!,
      commerce = commerceWithReadableMap(template.getMap("commerce"))!!,
      buttonTitle = template.getString("button_title"),
      buttons = buttonsWithReadableArray(template.getArray("buttons"))
    )
    LinkClient.instance.defaultTemplate(reactContext, defaultFeed) { linkResult, error ->
      if (error != null) {
        promise.reject("RNKakaoLink commerceLink", error)
      }
      else if (linkResult != null) {
        startActivity(reactContext, linkResult.intent, null)
        promise.resolve(toResultMap(linkResult))
      }
    }
    return@commerceLink
  }

  @ReactMethod
  private fun textLink(template: ReadableMap, promise: Promise) {
    val defaultFeed = TextTemplate(
      text = template.getString("text")!!,
      link = linkWithReadableMap(template.getMap("link"))!!,
      buttonTitle = template.getString("button_title"),
      buttons = buttonsWithReadableArray(template.getArray("buttons"))
    )
    LinkClient.instance.defaultTemplate(reactContext, defaultFeed) { linkResult, error ->
      if (error != null) {
        promise.reject("RNKakaoLink textLink", error)
      }
      else if (linkResult != null) {
        startActivity(reactContext, linkResult.intent, null)
        promise.resolve(toResultMap(linkResult))
      }
    }
    return@textLink
  }
}
