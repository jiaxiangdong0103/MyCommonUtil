package cn.com.jxd.commonutil.utils

import android.content.Context
import cn.com.jxd.commonutil.log.LogUtils
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.PermissionDef

/**
 * @author xiangdong.jia
 * @description:
 * @date :2021/5/31 下午3:40
 */
class SYPermissionUtils {
    companion object {
        fun builder(context: Context) = Builder(context)
    }

    class Builder(private val context: Context) {

        private var mPermission: Array<String>? = null
        private var granted: (() -> Unit)? = null
        private var denied: (() -> Unit)? = null

        fun permission(@PermissionDef vararg permission: String): Builder {
            mPermission = arrayOf(*permission)
            return this
        }

        fun onGranted(granted: (() -> Unit)): Builder {
            this.granted = granted
            return this
        }

        fun onDenied(denied: (() -> Unit)): Builder {
            this.denied = denied
            return this
        }

        fun start() {
            if (mPermission == null) {
                LogUtils.e("请使用permission()设置权限")
                return
            }
            if(SYPermissionUtils().hasPermission(context, *mPermission!!)){
                granted?.invoke()
                LogUtils.e("已经有相关权限")
                return
            }
            AndPermission.with(context)
                .runtime()
                .permission(mPermission)
                .onGranted {
                    granted?.invoke()
                }
                .onDenied {
                    denied?.invoke()
                }
                .start()
        }

    }

    /**
     * 检查权限
     * @param content Context
     * @param permission Array<out String>
     * @return Boolean
     */
    fun hasPermission(content: Context, @PermissionDef vararg permission: String): Boolean {
        return AndPermission.hasPermissions(content, permission)
    }

    /**
     * 有些特权永久禁用，可能需要在执行时设置
     * @param content Context
     * @param permission Array<out String>
     * @return Boolean
     */
    fun hasAlwaysDeniedPermission(
        content: Context,
        @PermissionDef vararg permission: String
    ): Boolean {
        return AndPermission.hasAlwaysDeniedPermission(content, permission.toList())
    }

}