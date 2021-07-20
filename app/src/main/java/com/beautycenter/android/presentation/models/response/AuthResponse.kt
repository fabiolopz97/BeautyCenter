package com.beautycenter.android.presentation.models.response

import com.beautycenter.android.presentation.models.Customer

data class AuthResponse (
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val user: Customer,
    val message: String
)