package com.sum.login.di

import com.sum.login.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by luyao
 * on 2019/11/15 15:44
 */

val moduleLogin = module {
   /* single {
        RetrofitManager.initRetrofit().getService(CommonApi::class.java)
    }*/

    viewModel { LoginViewModel() }
}