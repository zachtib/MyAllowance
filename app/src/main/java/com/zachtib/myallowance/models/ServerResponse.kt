package com.zachtib.myallowance.models

data class ServerResponse<T>(
    val data: T?,
    val error: ServerError?
)