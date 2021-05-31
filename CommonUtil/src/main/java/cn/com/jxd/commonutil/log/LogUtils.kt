package cn.com.jxd.commonutil.log

import android.util.Log
import androidx.annotation.Nullable

/**
 * @author xiangdong.jia
 * @description:
 * @date :2020/11/5 下午4:15
 */
class LogUtils {
    companion object {
        fun e(@Nullable value: Any) {
            Log.e("error", "$value")
        }
        fun j(@Nullable value: Any) {
            Log.e("jia ${Thread.currentThread().name}", "$value")
        }
        fun d(@Nullable value: Any) {
            Log.e("jia ${Thread.currentThread().name}", "$value")
        }

    }
}