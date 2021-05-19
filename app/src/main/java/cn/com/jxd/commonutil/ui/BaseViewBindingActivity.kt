package cn.com.jxd.commonutil.ui

import androidx.viewbinding.ViewBinding

/**
 * @author xiangdong.jia
 * @description:想使用ViewBinding的Activity基类
 * @date :2021/4/27 下午3:32
 */
abstract class BaseViewBindingActivity<T : ViewBinding> : BaseActivity() {

    protected lateinit var mViewBinding: T

    protected abstract fun createViewBinding(): ViewBinding

    override fun initRootLayout(): Int {
        mViewBinding = createViewBinding() as T
        setContentView(mViewBinding.root)
        return 0
    }

}