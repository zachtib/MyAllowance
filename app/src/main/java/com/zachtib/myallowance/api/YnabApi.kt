package com.zachtib.myallowance.api

import com.zachtib.myallowance.models.Budgets
import com.zachtib.myallowance.models.Categories
import com.zachtib.myallowance.models.ServerResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface YnabApi {
    @GET("budgets")
    suspend fun getBudgets(@Header("Authorization") authorization: String): ServerResponse<Budgets>

    @GET("budgets/{budget_id}/categories")
    suspend fun getCategories(@Header("Authorization") authorization: String,
                              @Path("budget_id") budgetId: String): ServerResponse<Categories>
}