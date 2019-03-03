package com.zachtib.myallowance.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun shouldRedirectToSetup(): Boolean {
        return true
    }
}