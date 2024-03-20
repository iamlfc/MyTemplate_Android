package com.leif.login.service

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.leif.common.constant.LOGIN_SERVICE_LOGIN
import com.leif.common.provider.UserServiceProvider
import com.leif.common.service.ILoginService
import com.leif.framework.log.LogUtil
import com.leif.framework.toast.TipsToast
import com.leif.main.ui.login.login.LoginActivity
import com.leif.main.ui.login.policy.PrivacyPolicyActivity
import com.leif.network.manager.ApiManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author mingyan.su
 * @date   2023/3/25 13:41
 * @desc   提供对ILoginService接口的具体实现
 */
@Route(path = LOGIN_SERVICE_LOGIN)
class LoginService : ILoginService {

    /**
     * 是否登录
     * @return Boolean
     */
    override fun isLogin(): Boolean {
        return UserServiceProvider.isLogin()
    }

    /**
     * 跳转登录页
     * @param context
     */
    override fun login(context: Context) {
        context.startActivity(Intent(context, LoginActivity::class.java))
    }

    /**
     * 登出
     */
    override fun logout(
        context: Context,
        lifecycleOwner: LifecycleOwner?,
        observer: Observer<Boolean>
    ) {
        val scope = lifecycleOwner?.lifecycleScope ?: GlobalScope
        scope.launch {
            val response = ApiManager.api.logout()
            if (response?.isFailed() == true) {
                TipsToast.showTips(response.errorMsg)
                return@launch
            }
            LogUtil.e("logout${response?.data}", tag = "smy")
            observer.onChanged(response?.isFailed() == true)
            login(context)
        }
    }

    /**
     * 跳转隐私协议页
     * @param context
     */
    override fun readPolicy(context: Context) {
        context.startActivity(Intent(context, PrivacyPolicyActivity::class.java))
    }

    override fun init(context: Context?) {

    }
}