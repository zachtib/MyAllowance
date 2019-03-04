package com.zachtib.myallowance.ui.main

import androidx.lifecycle.ViewModel
import com.zachtib.myallowance.AllowancePreferences
import com.zachtib.myallowance.service.YnabService

class MainViewModel(
    private val service: YnabService,
    private val preferences: AllowancePreferences
) : ViewModel() {
    fun shouldRedirectToSetup(): Boolean {
        return !preferences.isConfigured
    }

    suspend fun updateBalance() {

    }
}