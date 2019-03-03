package com.zachtib.myallowance.ui.setup

import androidx.lifecycle.ViewModel
import com.zachtib.myallowance.models.Budget
import com.zachtib.myallowance.models.Budgets
import com.zachtib.myallowance.service.YnabService

class SetupViewModel(private val service: YnabService) : ViewModel() {
    suspend fun onAuthorizedClicked(auth: String) {
        service.authenticate(auth)
    }

    suspend fun getBudgets(): List<Budget> {
        return service.getBudgets()
    }
}