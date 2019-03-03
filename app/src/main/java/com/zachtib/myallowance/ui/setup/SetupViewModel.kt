package com.zachtib.myallowance.ui.setup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zachtib.android.mutableLiveDataOf
import com.zachtib.myallowance.models.Budget
import com.zachtib.myallowance.models.Budgets
import com.zachtib.myallowance.service.YnabService

class SetupViewModel(private val service: YnabService) : ViewModel() {

    private val state = mutableLiveDataOf(SetupViewState())

    fun getState(): LiveData<SetupViewState> = state

    var developerToken: String = ""
        set(value) {
            field = value
            updateState { copy(
                authorizedButtonEnabled = value.isNotBlank()
            ) }
        }

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
    }

    fun onBudgetSelectionCleared() {

    }

    fun onBudgetItemSelected(position: Int) {

    }
}