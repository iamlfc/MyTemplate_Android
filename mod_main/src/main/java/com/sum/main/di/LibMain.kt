package com.sum.main.di

import com.sum.main.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val moduleMain = module {
    viewModel { MainViewModel() }
}