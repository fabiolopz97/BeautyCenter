package com.beautycenter.android.presentation.models

import com.google.gson.annotations.SerializedName

data class Auth (
    @SerializedName("email")
    val user: String,
    @SerializedName("password")
    val password: String
)