package com.zachtib.myallowance

import com.zachtib.myallowance.service.YnabService
import com.zachtib.myallowance.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { YnabService(get()) }

    viewModel { MainViewModel() }
}