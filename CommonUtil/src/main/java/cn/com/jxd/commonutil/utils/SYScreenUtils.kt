package cn.com.jxd.commonutil.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

/**
 * @author xiangdong.jia
 * @description:
 * @date :2021/5/10 下午3:32
 */
object SYScreenUtils {

    /**
     * dp转px
     *
     * @param dpValue
     * @return
     */
    fun dp2px(dpValue: Float): Float {
        val scale = Resources.getSystem().displayMetrics.density
        return dpValue * scale + 0.5f
    }

    /**
     * px转dp
     *
     * @param pxValue
     * @return
     */
    fun px2dp(pxValue: Float): Float {
        val scale = Resources.getSystem().displayMetrics.density
        return pxValue / scale + 0.5f
    }

    /**
     * sp转px
     *
     * @param spValue
     * @return
     */
    fun sp2px(spValue: Float): Float {
        val fontScale = Resources.getSystem().displayMetrics.scaledDensity
        return spValue * fontScale + 0.5f
    }

    /**
     * px转sp
     *
     * @param pxValue
     * @return
     */
    fun px2sp(pxValue: Float): Float {
        val fontScale = Resources.getSystem().displayMetrics.scaledDensity
        return pxValue / fontScale + 0.5f
    }

    /**
     * px转所有
     *
     * @param value px
     * @param unit  转换类型
     * @return
     */
    fun applyDimension(value: Float, unit: Int): Float {
        val metrics = Resources.getSystem().displayMetrics
        when (unit) {
            TypedValue.COMPLEX_UNIT_PX -> return value
            TypedValue.COMPLEX_UNIT_DIP -> return value * metrics.density
            TypedValue.COMPLEX_UNIT_SP -> return value * metrics.scaledDensity
            TypedValue.COMPLEX_UNIT_PT -> return value * metrics.xdpi * (1.0f / 72)
            TypedValue.COMPLEX_UNIT_IN -> return value * metrics.xdpi
            TypedValue.COMPLEX_UNIT_MM -> return value * metrics.xdpi * (1.0f / 25.4f)
        }
        return 0f
    }

    /**
     * 在 onCreate 中获取视图的尺寸
     *
     * @param view
     * @param listener
     */
    fun forceGetViewSize(view: View, listener: onGetSizeListener?) {
        view.post { listener?.onGetSize(view) }
    }

    /**
     * 测量视图宽度
     *
     * @param view
     * @return
     */
    fun getMeasuredWidth(view: View): Int {
        return measureView(view)[0]
    }

    /**
     * 测量视图高度
     *
     * @param view
     * @return
     */
    fun getMeasuredHeight(view: View): Int {
        return measureView(view)[1]
    }

    /**
     * 测量视图尺寸
     *
     * @param view
     * @return arr[0]: width, arr[1]: height
     */
    fun measureView(view: View): IntArray {
        var lp = view.layoutParams
        if (lp == null) {
            lp = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        val widthSpec = ViewGroup.getChildMeasureSpec(0, 0, lp.width)
        val lpHeight = lp.height
        val heightSpec: Int
        heightSpec = if (lpHeight > 0) {
            View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY)
        } else {
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        }
        view.measure(widthSpec, heightSpec)
        return intArrayOf(view.measuredWidth, view.measuredHeight)
    }

    /**
     * 获取屏幕的宽度（单位：px）
     *
     * @return
     */
    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.defaultDisplay.getRealSize(point)
        } else {
            wm.defaultDisplay.getSize(point)
        }
        return point.x
    }

    /**
     * 获取屏幕的高度（单位：px）
     *
     * @return
     */
    fun getScreenHeight(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.defaultDisplay.getRealSize(point)
        } else {
            wm.defaultDisplay.getSize(point)
        }
        return point.y
    }

    /**
     * 获取是否是竖屏
     *
     * @param context
     * @return
     */
    fun isPortrait(context: Context): Boolean {
        return (context.resources.configuration.orientation
                == Configuration.ORIENTATION_PORTRAIT)
    }

    /**
     * 获取屏幕密度
     *
     * @return
     */
    fun getScreenDensity(): Float {
        return Resources.getSystem().displayMetrics.density
    }

    /**
     * 获取屏幕密度 DPI
     *
     * @return
     */
    fun getScreenDensityDpi(): Int {
        return Resources.getSystem().displayMetrics.densityDpi
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    fun getStatusBarHeight(): Int {
        val resources = Resources.getSystem()
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }

    /**
     * 是否在屏幕右侧
     *
     * @param mContext 上下文
     * @param xPos     位置的x坐标值
     * @return true：是
     */
    fun isInRight(mContext: Context, xPos: Int): Boolean {
        return xPos > getScreenWidth(mContext) / 2
    }

    /**
     * 是否在屏幕左侧
     *
     * @param mContext 上下文
     * @param xPos     位置的x坐标值
     * @return true：是
     */
    fun isInLeft(mContext: Context, xPos: Int): Boolean {
        return xPos < getScreenWidth(mContext) / 2
    }

    /**
     * 是否在View的右侧
     *
     * @param view 要判断的View
     * @param xPos 位置x坐标
     * @return true:是 false:不是
     */
    fun isInRight(view: View, xPos: Int): Boolean {
        return xPos > view.measuredWidth / 2
    }

    /**
     * 是否在View的左侧
     *
     * @param view 要判断的View
     * @param xPos 位置x坐标
     * @return true:是 false:不是
     */
    fun isInLeft(view: View, xPos: Int): Boolean {
        return xPos < view.measuredWidth / 2
    }

    /**
     * 精确获取屏幕尺寸（例如：3.5、4.0、5.0寸屏幕）
     *
     * @param ctx
     * @return
     */
    fun getScreenPhysicalSize(ctx: Context): Double {
        val dm = DisplayMetrics()
        (ctx.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(dm)
        val diagonalPixels = Math.sqrt(
            Math.pow(
                dm.widthPixels.toDouble(),
                2.0
            ) + Math.pow(dm.heightPixels.toDouble(), 2.0)
        )
        return diagonalPixels / (160 * dm.density)
    }

    interface onGetSizeListener {
        fun onGetSize(view: View?)
    }
}