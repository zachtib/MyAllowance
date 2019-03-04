package com.zachtib.myallowance.ui.setup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zachtib.android.mutableLiveDataOf
import com.zachtib.myallowance.AllowancePreferences
import com.zachtib.myallowance.Either
import com.zachtib.myallowance.models.Budget
import com.zachtib.myallowance.models.Category
import com.zachtib.myallowance.models.CategoryGroup
import com.zachtib.myallowance.service.YnabService
import com.zachtib.myallowance.unfurl

class SetupViewModel(private val service: YnabService, private val preferences: AllowancePreferences) : ViewModel() {

    private val state = mutableLiveDataOf(SetupViewState())

    fun getState(): LiveData<SetupViewState> = state

    var developerToken: String = ""
        set(value) {
            field = value
            updateState { copy(
                authorizedButtonEnabled = value.isNotBlank()
            ) }
        }

    private var budgets: List<Budget> = listOf()
    private var categories: List<Either<CategoryGroup, Category>> = listOf()
    private var selectedCategory: Category? = null

    private fun updateState(transformation: SetupViewState.() -> SetupViewState) {
        state.value?.let { currentState ->
            val newState = currentState.transformation()
            state.postValue(newState)
        }
    }

    suspend fun onAuthorizedClicked() {
        service.authenticate(developerToken)
        val budgets = service.getBudgets()
        updateState { copy(
            budgets = budgets
        ) }
        this.budgets = budgets
    }

    fun onBudgetSelectionCleared() {
        updateState { copy(
            selectedBudgetIndex = -1,
            selectedCategoryIndex = -1,
            categories = listOf()
        ) }
    }

    suspend fun onBudgetItemSelected(position: Int) {
        val selectedBudget = budgets[position]
        val categories = service.getCategories(selectedBudget).unfurl { categories }
        updateState { copy(
            selectedBudgetIndex = position,
            categories = categories
        ) }
        this.categories = categories
    }

    fun onCategorySelectionCleared() {
        updateState { copy(
            selectedCategoryIndex = -1
        ) }
    }

    fun onCategorySelected(position: Int) {
        val selectedItem = categories[position]
        if (selectedItem is Either.Right) {
            selectedCategory = selectedItem.value
            updateState { copy(
                selectedCategoryIndex = position,
                saveButtonEnabled = true
            ) }
        }
        updateState { copy(selectedCategoryIndex = position) }
    }

    fun onSaveButtonPressed() {
        selectedCategory?.let {chosenCategory ->
            preferences.developerToken = developerToken
            preferences.chosenCategory = chosenCategory.id
            preferences.isConfigured = true

            updateState { copy(
                finished = true
            ) }
            return
        }
        updateState { copy(
            saveButtonEnabled = false
        ) }
    }
}