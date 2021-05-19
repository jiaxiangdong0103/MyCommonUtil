package cn.com.jxd.commonutil.router

import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author xiangdong.jia
 * @description: 跳转页面的工具类
 * @date :2021/4/27 下午5:36
 */
object PageJumpUtil {
    @JvmStatic
    fun gotoActivity(RouterPath: String) {
        ARouter.getInstance().build(RouterPath).navigation()
    }
}