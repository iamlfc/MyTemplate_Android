package com.leif.main.di

import com.leif.login.login.LoginViewModel
import com.leif.main.ui.category.viewmodel.CategoryViewModel
import com.leif.main.ui.home.viewmodel.HomeViewModel
import com.leif.main.ui.mine.viewmodel.MineViewModel
import com.leif.main.ui.system.viewmodel.SystemViewModel
import com.leif.main.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val moduleMain = module {
    viewModel { MainViewModel() }
    viewModel { LoginViewModel() }
    viewModel { HomeViewModel() }
    viewModel { CategoryViewModel() }
    viewModel { SystemViewModel() }
    viewModel { MineViewModel() }
}

//val repositoryMain = module {
////    single { WanRetrofitClient.getService(WanService::class.java, WanService.BASE_URL) }
////    single { CoroutinesDispatcherProvider() }
////    single { LoginRepository(get()) }
////    single { SquareRepository() }
//    single { HomeRepository() }
//    single { LoginRepository() }
//
//}

val appModule = listOf(moduleMain)
