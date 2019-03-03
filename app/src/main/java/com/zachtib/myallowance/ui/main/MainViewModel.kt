package com.zachtib.myallowance.ui.main

import androidx.lifecycle.ViewModel
import com.zachtib.myallowance.AllowancePreferences

class MainViewModel(
    private val preferences: AllowancePreferences
) : ViewModel() {
    fun shouldRedirectToSetup(): Boolean {
        return !preferences.isConfigured
    }
}