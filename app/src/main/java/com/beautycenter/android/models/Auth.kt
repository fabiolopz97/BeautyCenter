package com.beautycenter.android.models

import com.google.gson.annotations.SerializedName

class Auth (
    @SerializedName("email")
    val user: String,
    @SerializedName("password")
    val password: String
)