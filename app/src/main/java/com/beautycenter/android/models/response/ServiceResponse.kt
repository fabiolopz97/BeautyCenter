package com.beautycenter.android.models.response

import com.beautycenter.android.models.Service
import com.google.gson.annotations.SerializedName

class ServiceResponse (
    val code: Int,
    val status: String,
    @SerializedName("data")
    val results: List<Service>,
    val message: String
)