package com.zachtib.myallowance

import android.content.Context
import com.zachtib.myallowance.api.YnabApi
import com.zachtib.myallowance.service.YnabService
import com.zachtib.myallowance.ui.main.MainViewModel
import com.zachtib.myallowance.ui.setup.SetupViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


val appModule = module {

    single {
        val context: Context = get()
        val prefName = context.getString(R.string.preferences)
        val sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        AllowancePreferences(sharedPreferences)
    }

    single<YnabApi> {
        val context: Context = get()

        Retrofit.Builder()
            .baseUrl(context.getString(R.string.ynab_api))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    single { YnabService(get(), get()) }

    viewModel { MainViewModel(get()) }
    viewModel { SetupViewModel(get()) }
}