package com.zachtib.myallowance.api

import com.zachtib.myallowance.models.Budgets
import com.zachtib.myallowance.models.ServerResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface YnabApi {
    @GET("budgets")
    suspend fun getBudgets(@Header("Authorization") authorization: String): ServerResponse<Budgets>
}