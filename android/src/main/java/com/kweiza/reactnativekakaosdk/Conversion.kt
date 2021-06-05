
package com.kweiza.reactnativekakaosdk

import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.ReadableType
import java.util.*

public final class Conversion {
  companion object {
    fun toObject(readableMap: ReadableMap?, key: String): Any? {
      if (readableMap == null) {
        return null
      }
      var result: Any?
      val readableType = readableMap.getType(key)
      when (readableType) {
        ReadableType.Null -> result = key
        ReadableType.Boolean -> result = readableMap.getBoolean(key)
        ReadableType.Number -> {
          // Can be int or double.
          val tmp = readableMap.getDouble(key)
          if (tmp == tmp.toInt() as Double) {
            result = tmp.toInt()
          } else {
            result = tmp
          }
        }
        ReadableType.String -> result = readableMap.getString(key)
        ReadableType.Map -> result = toMap(readableMap.getMap(key))
        ReadableType.Array -> result = toList(readableMap.getArray(key))
        else -> throw IllegalArgumentException("Could not convert object with key: $key.")
      }
      return result
    }

    fun toMap(readableMap: ReadableMap?): kotlin.collections.Map<String, Any?>? {
      if (readableMap == null) {
        return null
      }
      val iterator = readableMap.keySetIterator()
      if (!iterator.hasNextKey()) {
        return null
      }
      val result: MutableMap<String, Any?> = HashMap()
      while (iterator.hasNextKey()) {
        val key = iterator.nextKey()
        result[key] = toObject(readableMap, key)
      }
      return result
    }

    fun toList(readableArray: ReadableArray?): MutableList<Any?>? {
      if (readableArray == null) {
        return null
      }
      var result: MutableList<Any?>? = ArrayList(readableArray.size())
      for (index in 0 until readableArray.size()) {
        val readableType = readableArray.getType(index)
        when (readableType) {
          ReadableType.Null -> result!!.add(index.toString())
          ReadableType.Boolean -> result!!.add(readableArray.getBoolean(index))
          ReadableType.Number -> {
            // Can be int or double.
            val tmp = readableArray.getDouble(index)
            if (tmp == tmp.toInt() as Double) {
              result!!.add(tmp.toInt())
            } else {
              result!!.add(tmp)
            }
          }
          ReadableType.String -> result!!.add(readableArray.getString(index))
          ReadableType.Map -> result!!.add(toMap(readableArray.getMap(index)))
          ReadableType.Array -> result = toList(readableArray.getArray(index))
          else -> throw IllegalArgumentException("Could not convert object with index: $index.")
        }
      }
      return result
    }
  }
}
