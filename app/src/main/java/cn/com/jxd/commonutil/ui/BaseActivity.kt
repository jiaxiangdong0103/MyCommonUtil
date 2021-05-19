package cn.com.jxd.commonutil.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author xiangdong.jia
 * @description: Activity的一个基类
 * @date :2021/4/27 下午3:32
 */
abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var mActivity: BaseActivity

    protected abstract fun initRootLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        if(initRootLayout()!=0){
            setContentView(initRootLayout())
        }
        initView()
        initData()
    }

    protected abstract fun initView()

    protected abstract fun initData()
}