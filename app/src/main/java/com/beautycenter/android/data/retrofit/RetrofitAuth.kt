package com.beautycenter.android.data.retrofit

import com.beautycenter.android.data.PathUtils
import com.beautycenter.android.data.service.AuthService
import com.beautycenter.android.data.service.CustomersService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAuth {
    private val auth = Retrofit.Builder()
        .baseUrl(PathUtils.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getAuthService(): AuthService {
        return  auth.create(AuthService::class.java)
    }
}