package com.zachtib.myallowance

import android.content.Context
import com.zachtib.myallowance.service.YnabService
import com.zachtib.myallowance.ui.main.MainViewModel
import com.zachtib.myallowance.ui.setup.SetupViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val appModule = module {

    single {
        val context: Context = get()
        val prefName = context.getString(R.string.preferences)
        val sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        AllowancePreferences(sharedPreferences)
    }

    single { YnabService(get(), get()) }

    viewModel { MainViewModel(get()) }
    viewModel { SetupViewModel(get()) }
}