package com.beautycenter.android.presentation.models.response

import com.beautycenter.android.presentation.models.Customer
import com.google.gson.annotations.SerializedName

data class CustomerResponse (
    val code: Int,
    val status: String,
    @SerializedName("data")
    val results: List<Customer>,
    val message: String
)