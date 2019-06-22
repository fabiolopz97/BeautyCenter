package com.beautycenter.android.models.response

import com.beautycenter.android.models.Customer
import com.google.gson.annotations.SerializedName

class CustomerResponse (
    val code: Int,
    val status: String,
    @SerializedName("data")
    val results: List<Customer>,
    val message: String
)