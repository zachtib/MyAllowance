package com.zachtib.myallowance.service

import com.zachtib.myallowance.AllowancePreferences
import com.zachtib.myallowance.api.YnabApi
import com.zachtib.myallowance.models.Budget
import com.zachtib.myallowance.models.Budgets
import kotlinx.coroutines.delay


class YnabService(private val api: YnabApi, private val preferences: AllowancePreferences) {
    fun isAuthenticated() = !preferences.developerToken.isBlank()

    suspend fun authenticate(developerToken: String): Boolean {
        // TODO: Test the token first!
        delay(5) // Just to make this suspend for now

        preferences.developerToken = developerToken

        return isAuthenticated()
    }

    private fun getAuthorization() = "Bearer ${preferences.developerToken}"

    suspend fun getBudgets(): List<Budget> {
        return if (!isAuthenticated()) {
            listOf()
        } else {
            val serverResponse = api.getBudgets(getAuthorization())
            serverResponse.data.budgets
        }
    }
}