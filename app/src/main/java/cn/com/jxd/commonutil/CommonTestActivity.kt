package cn.com.jxd.commonutil
import cn.com.jxd.commonutil.databinding.CommonTestActivityBinding
import cn.com.jxd.commonutil.ui.BaseViewBindingActivity

/**
 * @author xiangdong.jia
 * @description: 测试
 * @date :2021/5/19 下午2:38
 */
class CommonTestActivity : BaseViewBindingActivity<CommonTestActivityBinding>() {
    override fun createViewBinding() = CommonTestActivityBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun initData() {
        mViewBinding.tvTest.setOnClickListener {

        }
    }

}