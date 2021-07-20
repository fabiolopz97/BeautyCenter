package com.beautycenter.android.data

import com.beautycenter.android.data.network.AuthApi
import com.beautycenter.android.data.network.RetrofitClient
import com.beautycenter.android.domain.AuthRepository
import com.beautycenter.android.presentation.models.Auth
import com.beautycenter.android.presentation.models.Customer
import com.beautycenter.android.presentation.models.response.AuthResponse
import io.reactivex.Observable

class AuthRepositoryImpl: AuthRepository {

    private val authApi = RetrofitClient()
        .getRetrofitClient()
        .create(AuthApi::class.java)

    override fun login(auth: Auth): Observable<AuthResponse> {
        return authApi.login(auth).map {
            it
        }
    }

    override fun logout(token: String): Observable<AuthResponse> {
        return authApi.logout(token).map {
            it
        }
    }

    override fun refresh(token: String): Observable<AuthResponse> {
        return authApi.refresh(token).map {
            it
        }
    }

    override fun me(token: String): Observable<Customer> {
        return authApi.me(token).map {
            it
        }
    }
}