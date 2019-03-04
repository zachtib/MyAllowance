package com.zachtib.myallowance.service

import com.zachtib.myallowance.AllowancePreferences
import com.zachtib.myallowance.Cache
import com.zachtib.myallowance.api.YnabApi
import com.zachtib.myallowance.models.Budget
import com.zachtib.myallowance.models.Category
import com.zachtib.myallowance.models.CategoryGroup
import kotlinx.coroutines.delay
import timber.log.Timber


class YnabService(private val api: YnabApi, private val preferences: AllowancePreferences) {

    private val budgetsCache = Cache<List<Budget>>()
    private val categoriesCache = Cache<List<CategoryGroup>>()
    private var cachedBudget: Budget? = null

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
            budgetsCache.getValue {
                val serverResponse = api.getBudgets(getAuthorization())
                if (serverResponse.error != null) {
                    Timber.e(serverResponse.error.detail)
                }
                serverResponse.data?.budgets ?: listOf()
            }
        }
    }

    suspend fun getCategories(budget: Budget): List<CategoryGroup> {
        return if (!isAuthenticated()) {
            listOf()
        } else {
            if (cachedBudget != budget) {
                categoriesCache.expire()
                cachedBudget = budget
            }
            try {
                categoriesCache.getValue {
                    val response = api.getCategories(getAuthorization(), budget.id)
                    if (response.error != null) {
                        Timber.e(response.error.detail)
                    }
                    response.data?.category_groups ?: listOf()
                }
            } catch (e: retrofit2.HttpException) {
                Timber.e(e, "Error during getCategories()")
                listOf<CategoryGroup>()
            }
        }
    }

    suspend fun getCategoryDetail(budgetId: String, categoryId: String): Category? {
        return if (!isAuthenticated()) {
            null
        } else {
            api.getCategory(getAuthorization(), budgetId, categoryId).data?.category
        }
    }
}