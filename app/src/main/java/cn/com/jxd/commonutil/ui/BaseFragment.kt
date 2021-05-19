package cn.com.jxd.commonutil.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * @author xiangdong.jia
 * @description: Fragment的一个基类
 * @date :2021/4/27 下午3:32
 */
abstract class BaseFragment : Fragment() {

    protected lateinit var mActivity: AppCompatActivity

    protected abstract fun initRootLayout(): Int
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(initRootLayout(), container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }


    protected abstract fun initView(view: View)

    protected abstract fun initData()
    /**
     * fragment的生命周期是
     * onAttach()           和activity建立关系时调用
     * onCreate()
     * onCreateView()
     * onViewCreated()
     * onActivityCreated()
     * onStart()
     * onResume()
     * onPause()
     * onStop()
     * onDestroyView()      先移除
     * onDestroy()
     * onDetach()           和activity取消关系时调用
     */
}