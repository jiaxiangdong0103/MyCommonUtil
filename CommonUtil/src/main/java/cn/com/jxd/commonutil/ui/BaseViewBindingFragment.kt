package cn.com.jxd.commonutil.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * @author xiangdong.jia
 * @description:想使用ViewBinding的Fragment基类
 * @date :2021/4/27 下午3:32
 */
abstract class BaseViewBindingFragment<T : ViewBinding> : BaseFragment() {

    protected lateinit var mViewBinding: T

    protected abstract fun createViewBinding(): ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = createViewBinding() as T
        return mViewBinding.root
    }

    override fun initRootLayout(): Int {
        TODO("Not yet implemented")
    }
}