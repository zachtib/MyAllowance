package com.zachtib.myallowance

import android.app.Application
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class AllowanceApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin(this, listOf(appModule))
    }
}

