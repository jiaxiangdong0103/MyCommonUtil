package cn.com.jxd.commonutil.ui

import android.app.Application
import cn.com.jxd.commonutil.db.CacheStorage
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author xiangdong.jia
 * @description:
 * @date :2021/4/27 下午5:42
 */
class BaseApplication : Application() {

    private val mApplication by lazy {
        this
    }

    fun getApplication() = mApplication

    override fun onCreate() {
        super.onCreate()
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
        CacheStorage.init(this)
    }

}