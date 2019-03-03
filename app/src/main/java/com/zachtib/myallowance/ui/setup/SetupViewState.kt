package com.zachtib.myallowance.ui.setup

import com.zachtib.myallowance.Either
import com.zachtib.myallowance.models.Budget
import com.zachtib.myallowance.models.Category
import com.zachtib.myallowance.models.CategoryGroup

data class SetupViewState(
    val authorizedButtonEnabled: Boolean = false,
    val saveButtonEnabled: Boolean = false,
    val budgets: List<Budget> = listOf(),
    val categories: List<Either<CategoryGroup, Category>> = listOf(),
    val finished: Boolean = false
)