package com.beautycenter.android.data.retrofit

import com.beautycenter.android.data.PathUtils
import com.beautycenter.android.data.service.ServicesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    private val service = Retrofit.Builder()
        .baseUrl(PathUtils.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getServicesService(): ServicesService {
        return service.create(ServicesService::class.java)
    }
}