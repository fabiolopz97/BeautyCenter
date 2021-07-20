package com.beautycenter.android.domain

import com.beautycenter.android.presentation.models.Auth
import com.beautycenter.android.presentation.models.Customer
import com.beautycenter.android.presentation.models.response.AuthResponse
import io.reactivex.Observable

interface AuthRepository {
    fun login(auth: Auth): Observable<AuthResponse>
    fun logout(token: String): Observable<AuthResponse>
    fun refresh(token: String): Observable<AuthResponse>
    fun me(token: String): Observable<Customer>
}