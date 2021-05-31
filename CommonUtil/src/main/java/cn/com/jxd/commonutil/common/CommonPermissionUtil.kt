package cn.com.jxd.commonutil.common

import android.content.Context
import cn.com.jxd.commonutil.utils.SYPermissionUtils
import cn.com.jxd.commonutil.utils.ToastUtils
import com.yanzhenjie.permission.runtime.Permission

/**
 * @author xiangdong.jia
 * @description:
 * @date :2021/5/31 下午4:58
 */
object CommonPermissionUtil {
    fun checkStoragePermission(context: Context, consumer: ((it: Boolean) -> Unit)){
        SYPermissionUtils.builder(context)
            .permission(Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE)
            .onDenied {
                ToastUtils.show("请先开启存储权限")
                consumer.invoke(false)
            }
            .onGranted {
                consumer.invoke(true)
            }
            .start()
    }
}