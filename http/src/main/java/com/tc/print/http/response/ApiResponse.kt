package com.tc.print.http.response

data class ApiResponse<T>(
    val code: Int,
    val msg: String?,
    val data: T?
)