package com.leif.main.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.leif.common.provider.MainServiceProvider
import com.leif.framework.base.BaseDataBindActivity
import com.leif.framework.ext.countDownCoroutines
import com.leif.framework.ext.onClick
import com.leif.framework.utils.StatusBarSettingHelper
import com.leif.main.R
import com.leif.main.databinding.ActivitySplashBinding

/**
 * @author mingyan.su
 * @date   2023/3/29 14:25
 * @desc   启动页
 */
class SplashActivity : BaseDataBindActivity<ActivitySplashBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarSettingHelper.setStatusBarTranslucent(this)
        mBinding.tvSkip.onClick {
            MainServiceProvider.toMain(this)
        }
        //倒计时
        countDownCoroutines(2, lifecycleScope, onTick = {
            mBinding.tvSkip.text = getString(com.leif.common.R.string.splash_time, it.plus(1).toString())
        }) {
            MainServiceProvider.toMain(this)
            finish()
        }
    }
}