package cn.com.jxd.commonutil.db

import cn.com.jxd.base.db.CacheStorage
import com.tencent.mmkv.MMKV

/**
 * @author xiangdong.jia
 * @description:
 * @date :2021/5/10 下午3:37
 */
object DataStorage : CacheStorage() {

    private val mmkv by lazy {
        MMKV.defaultMMKV()
    }

    override fun mmkv(): MMKV? = mmkv


}