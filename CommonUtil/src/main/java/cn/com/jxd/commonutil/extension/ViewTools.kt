package cn.com.jxd.commonutil.extension

import android.view.View

/**
 * @author xiangdong.jia
 * @description: view的扩展属性
 * @date :2021/4/30 下午2:27
 */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
