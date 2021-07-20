package com.beautycenter.android.data.network

import com.beautycenter.android.presentation.models.Auth
import com.beautycenter.android.presentation.models.Customer
import com.beautycenter.android.presentation.models.response.AuthResponse
import io.reactivex.Observable
import retrofit2.http.*

interface AuthApi {
    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json;charset=utf-8",
        "Cache-Control: max-age=640000"
    )
    @POST("auth/login/")
    fun login(@Body auth: Auth): Observable<AuthResponse>

    /**
     * @param token: String
     * @return This method return a message within AuthResponse
     */
    @GET("auth/logout/{token}")
    fun logout(@Path("token") token: String): Observable<AuthResponse>

    @GET("auth/refresh/{token}")
    fun refresh(@Path("token") token: String): Observable<AuthResponse>

    @GET("auth/me/{token}")
    fun me(@Path("token") token: String): Observable<Customer>
}