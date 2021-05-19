package cn.com.jxd.commonutil.utils

import android.R
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt

/**
 * @author xiangdong.jia
 * @description:
 * @date :2021/5/10 下午3:34
 */
object SYViewUtil {
    /**
     * 通过比较详细的参数属性设置view的默认和选中样式
     *
     * @param view
     * @param stroke         边框宽度 默认dp
     * @param stroke_color_1 边框默认颜色
     * @param stroke_color_2 边框选中的颜色
     * @param bg_color_1     背景默认颜色
     * @param bg_color_2     背景选中状态的颜色
     * @param radius         圆角的大小，dp
     */
    fun setViewSelectorBg(
        view: View, stroke: Int,
        stroke_color_1: Int, stroke_color_2: Int,
        bg_color_1: Int, bg_color_2: Int,
        radius: Int
    ) {
        setViewSelectorBg(
            view, stroke,
            stroke_color_1, stroke_color_2,
            bg_color_1, bg_color_2,
            radius.toFloat(), radius.toFloat(),
            radius.toFloat(), radius.toFloat()
        )
    }

    /**
     * 设置viewd点击和显示的默认样式，这个是总方法，
     * 显示，点击时候的状态，主要就是两个色值
     * 圆角：传入每个角的圆角大小，四个圆角的顺序为左上，右上，右下，左下,并且四个角都为dp转px
     * 边宽：如果传入的边框不大于0 这不显示边框，边框同样有两个颜色,如果边框没有，那么颜色也用不到
     *
     * @param view
     * @param stroke         边框宽度 dp
     * @param stroke_color_1 边框默认颜色
     * @param stroke_color_2 边框选中的颜色
     * @param bg_color_1     背景默认颜色
     * @param bg_color_2     背景选中状态的颜色
     * @param radius_l_t     左上  dp
     * @param radius_r_t     右上  dp
     * @param radius_r_b     右下  dp
     * @param radius_l_b     左下  dp
     */
    fun setViewSelectorBg(
        view: View, stroke: Int,
        stroke_color_1: Int, stroke_color_2: Int,
        bg_color_1: Int, bg_color_2: Int,
        radius_l_t: Float, radius_r_t: Float,
        radius_r_b: Float, radius_l_b: Float
    ) {
        val lt = SYScreenUtils.dp2px(radius_l_t)
        val rt = SYScreenUtils.dp2px(radius_r_t)
        val rb = SYScreenUtils.dp2px(radius_r_b)
        val lb = SYScreenUtils.dp2px(radius_l_b)
        val strok = SYScreenUtils.dp2px(stroke.toFloat()).toInt()

        //1、2两个参数表示左上角，3、4表示右上角，5、6表示右下角，7、8表示左下角
        val radius = floatArrayOf(lt, lt, rt, rt, rb, rb, lb, lb)
        val normalDrawable = GradientDrawable()
        normalDrawable.setColor(bg_color_1)
        normalDrawable.cornerRadii = radius
        if (strok > 0) {
            normalDrawable.setStroke(strok, stroke_color_1)
        }
        val selectedDrawable = GradientDrawable()
        selectedDrawable.setColor(bg_color_2)
        selectedDrawable.cornerRadii = radius
        if (strok > 0) {
            selectedDrawable.setStroke(strok, stroke_color_2)
        }
        val stateListDrawable = StateListDrawable()
        stateListDrawable.addState(
            intArrayOf(-R.attr.state_focused, -R.attr.state_selected),
            normalDrawable
        )
        stateListDrawable.addState(
            intArrayOf(-R.attr.state_focused, R.attr.state_selected),
            selectedDrawable
        )
        stateListDrawable.addState(intArrayOf(R.attr.state_focused), selectedDrawable)
        view.background = stateListDrawable
    }

    /**
     * 设置View过渡背景
     *
     * @param v
     * @param colors 色值数组 @ColorInt
     * @param radius 圆角大小 dp
     */
    fun setViewGradientBg(v: View, colors: IntArray?, radius: Float) {
        val drawable = GradientDrawable()
        drawable.cornerRadius = SYScreenUtils.dp2px(radius)
        drawable.colors = colors
        drawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT //默认从左到右
        v.background = drawable
    }

    /**
     * 设置View背景
     *
     * @param v
     * @param color  色值
     * @param radius 圆角大小 dp
     */
    fun setViewGradientBg(v: View, @ColorInt color: Int, radius: Float) {
        setViewGradientBg(v, color, radius, radius, radius, radius)
    }


    /**
     * 设置View背景
     *
     * @param v
     * @param color 色值
     * @param lt    左上圆角 dp
     * @param rt    右上 dp
     * @param rb    右下 dp
     * @param lb    左下 dp
     */
    fun setViewGradientBg(
        v: View,
        @ColorInt color: Int,
        lt: Float,
        rt: Float,
        rb: Float,
        lb: Float
    ) {
        setViewGradientBg(v, color, lt, rt, rb, lb, 0f, 0)
    }


    /**
     * @param v
     * @param radius px
     * @param color
     */
    fun setViewGradientBg(v: View, @ColorInt color: Int, radius: FloatArray?) {
        setViewGradientBg(v, color, radius, 0f, 0)
    }

    /**
     * 设置View背景
     *
     * @param v
     * @param color      色值
     * @param lt         左上圆角 dp
     * @param rt         右上 dp
     * @param rb         右下 dp
     * @param lb         左下 dp
     * @param stokeColor @ColorInt
     * @param stokeDp
     */
    fun setViewGradientBg(
        v: View,
        color: Int,
        lt: Float,
        rt: Float,
        rb: Float,
        lb: Float,
        stokeDp: Float,
        @ColorInt stokeColor: Int
    ) {
        val lt1 = SYScreenUtils.dp2px(lt)
        val rt1 = SYScreenUtils.dp2px(rt)
        val rb1 = SYScreenUtils.dp2px(rb)
        val lb1 = SYScreenUtils.dp2px(lb)
        setViewGradientBg(
            v,
            color,
            floatArrayOf(lt1, lt1, rt1, rt1, rb1, rb1, lb1, lb1),
            stokeDp,
            stokeColor
        )
    }

    /**
     * @param v
     * @param radius     px
     * @param color
     * @param stokeDp    dp
     * @param stokeColor dp
     */
    fun setViewGradientBg(
        v: View,
        color: Int,
        radius: FloatArray?,
        stokeDp: Float,
        @ColorInt stokeColor: Int
    ) {
        val normalDrawable = GradientDrawable()
        normalDrawable.setColor(color)
        if (stokeDp > 0) {
            normalDrawable.setStroke(SYScreenUtils.dp2px(stokeDp).toInt(), stokeColor)
        }
        normalDrawable.cornerRadii = radius
        v.background = normalDrawable
    }

    /**
     * 设置按钮选中效果,可自定义颜色值，选中效果，点击没有效果;
     *
     * @param radius      圆角大小 dp
     * @param normalColor 为选中时颜色
     * @param selectColor 选中时颜色
     */
    fun setViewSelector(v: View, radius: Int, normalColor: Int, selectColor: Int) {
        val normalDrawable = GradientDrawable()
        normalDrawable.setColor(normalColor)
        normalDrawable.cornerRadius = SYScreenUtils.dp2px(radius.toFloat())
        val selectedDrawable = GradientDrawable()
        selectedDrawable.setColor(selectColor)
        selectedDrawable.cornerRadius = SYScreenUtils.dp2px(radius.toFloat())
        val stateListDrawable = StateListDrawable()
        stateListDrawable.addState(
            intArrayOf(-R.attr.state_focused, -R.attr.state_selected),
            normalDrawable
        )
        stateListDrawable.addState(
            intArrayOf(-R.attr.state_focused, R.attr.state_selected),
            selectedDrawable
        )
        stateListDrawable.addState(intArrayOf(R.attr.state_selected), selectedDrawable)
        v.background = stateListDrawable
    }

    fun setViewPressBackground(
        view: View,
        radius: Int,
        @ColorInt normalColor: Int,
        @ColorInt pressColor: Int
    ) {
        setViewPressBackground(
            view,
            radius.toFloat(),
            radius.toFloat(),
            radius.toFloat(),
            radius.toFloat(),
            normalColor,
            pressColor
        )
    }

    /**
     * 点击效果
     *
     * @param view
     * @param leftTop     dp
     * @param rightTop    dp
     * @param rightBottom dp
     * @param leftBottom  dp
     * @param normalColor
     * @param pressColor
     */
    fun setViewPressBackground(
        view: View,
        leftTop: Float,
        rightTop: Float,
        rightBottom: Float,
        leftBottom: Float,
        @ColorInt normalColor: Int,
        @ColorInt pressColor: Int
    ) {
        val lt = SYScreenUtils.dp2px(leftTop)
        val rt = SYScreenUtils.dp2px(rightTop)
        val rb = SYScreenUtils.dp2px(rightBottom)
        val lb = SYScreenUtils.dp2px(leftBottom)
        val radius = floatArrayOf(lt, lt, rt, rt, rb, rb, lb, lb)
        val normalDrawable = GradientDrawable()
        normalDrawable.setColor(normalColor)
        normalDrawable.cornerRadii = radius
        val clickDrawable = GradientDrawable()
        clickDrawable.setColor(pressColor)
        clickDrawable.cornerRadii = radius
        val stateListDrawable = StateListDrawable()
        stateListDrawable.addState(intArrayOf(R.attr.state_pressed), clickDrawable)
        stateListDrawable.addState(intArrayOf(-R.attr.state_pressed), normalDrawable)
        view.background = stateListDrawable
    }

    fun setViewWH(view: View, W: Int, H: Int) {
        var params = view.layoutParams
        if (params == null) {
            params = ViewGroup.LayoutParams(W, H)
        } else {
            params.width = W
            params.height = H
        }
        view.layoutParams = params
    }

    fun getViewWidth(view: View): Int {
        var width = view.width
        if (width > 0) {
            return width
        }
        width = view.measuredWidth
        if (width > 0) {
            return width
        }
        val params = view.layoutParams
        return params?.width ?: width
    }

    /**
     * 获取图片的实际宽度
     */
    fun getViewHeight(view: View): Int {
        var height = view.height
        if (height > 0) {
            return height
        }
        height = view.measuredHeight
        if (height > 0) {
            return height
        }
        val params = view.layoutParams
        return params?.height ?: height
    }
}