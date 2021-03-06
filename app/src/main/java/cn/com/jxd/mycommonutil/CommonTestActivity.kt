package cn.com.jxd.mycommonutil
import cn.com.jxd.commonutil.common.CommonPermissionUtil
import cn.com.jxd.commonutil.log.LogUtils
import cn.com.jxd.commonutil.ui.BaseViewBindingActivity
import cn.com.jxd.commonutil.utils.SYPermissionUtils
import cn.com.jxd.mycommonutil.databinding.CommonTestActivityBinding
import com.yanzhenjie.permission.runtime.Permission

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
            LogUtils.d("这是一个点击事件")
            CommonPermissionUtil.checkStoragePermission(this){
                if(it){//授权成功
                    // 这里写后续的逻辑
                }
            }
        }

    }

}