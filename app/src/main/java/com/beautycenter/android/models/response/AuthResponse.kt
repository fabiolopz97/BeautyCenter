package com.beautycenter.android.models.response

import com.beautycenter.android.models.Customer

class AuthResponse (
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val user: Customer,
    val message: String
)