package cn.com.jxd.commonutil.utils

import android.widget.Toast
import cn.com.jxd.commonutil.ui.BaseApplication

/**
 * @author xiangdong.jia
 * @description:
 * @date :2021/5/31 下午5:18
 */
object ToastUtils {
    fun show(text: CharSequence?) {
        Toast.makeText(BaseApplication().applicationContext, text, Toast.LENGTH_LONG)
    }
}