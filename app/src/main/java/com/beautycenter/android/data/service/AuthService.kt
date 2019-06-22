package com.beautycenter.android.data.service

import com.beautycenter.android.models.Auth
import com.beautycenter.android.models.Customer
import com.beautycenter.android.models.response.AuthResponse
import retrofit2.Call
import retrofit2.http.*

interface AuthService {
    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json;charset=utf-8",
        "Cache-Control: max-age=640000"
    )

    @POST("auth/login/")
    fun login(@Body auth: Auth): Call<AuthResponse>

    /**
     * @param token: String
     * @return This method return a message within AuthResponse
     */
    @GET("auth/logout/{token}")
    fun logout(@Path("token") token: String): Call<AuthResponse>

    @GET("auth/refresh/{token}")
    fun refresh(@Path("token") token: String): Call<AuthResponse>

    @GET("auth/me/{token}")
    fun me(@Path("token") token: String): Call<Customer>
}