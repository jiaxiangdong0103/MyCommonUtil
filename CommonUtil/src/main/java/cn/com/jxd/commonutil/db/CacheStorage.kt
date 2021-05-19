package cn.com.jxd.base.db

import android.content.Context
import com.tencent.mmkv.MMKV

/**
 * @author xiangdong.jia
 * @description:
 * @date :2021/5/10 下午3:44
 */
abstract class CacheStorage {
    abstract fun mmkv(): MMKV?

    companion object {
        fun init(context: Context) {
            MMKV.initialize(context.applicationContext)
        }
    }

    fun put(key: String, value: Any) {
        when (value) {
            is Int -> {
                mmkv()?.encode(key, value)
            }
            is Float -> {
                mmkv()?.encode(key, value)
            }
            is Double -> {
                mmkv()?.encode(key, value)
            }
            is Long -> {
                mmkv()?.encode(key, value)
            }
            is Boolean -> {
                mmkv()?.encode(key, value)
            }
            is String -> {
                mmkv()?.encode(key, value)
            }
        }
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return mmkv()?.getString(key, defaultValue) ?: defaultValue
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return mmkv()?.getBoolean(key, defaultValue)?: defaultValue
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return mmkv()?.getInt(key, defaultValue)?: defaultValue
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return mmkv()?.getFloat(key, defaultValue)?: defaultValue
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return mmkv()?.getLong(key, defaultValue)?: defaultValue
    }

}