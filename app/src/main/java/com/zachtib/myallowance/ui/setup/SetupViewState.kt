package com.zachtib.myallowance.ui.setup

import com.zachtib.myallowance.models.Budget

data class SetupViewState(
    val authorizedButtonEnabled: Boolean = false,
    val budgets: List<Budget> = listOf()
)