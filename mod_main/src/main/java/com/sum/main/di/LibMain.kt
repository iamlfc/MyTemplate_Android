package com.sum.main.di

import com.sum.login.login.LoginRepository
import com.sum.login.login.LoginViewModel
import com.sum.main.repository.HomeRepository
import com.sum.main.ui.category.viewmodel.CategoryViewModel
import com.sum.main.ui.home.viewmodel.HomeViewModel
import com.sum.main.ui.mine.viewmodel.MineViewModel
import com.sum.main.ui.system.viewmodel.SystemViewModel
import com.sum.main.viewModel.MainViewModel
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
